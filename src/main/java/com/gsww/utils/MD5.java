package com.gsww.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * MD5
 * com.gsww.utils
 *
 * @author Xander
 * 研究MD5加密机制
 * @Date 2018-02-27 下午2:23
 * The word 'impossible' is not in my dictionary.
 */
public class MD5 {
    static final byte[] PADDING = new byte[]{-128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private long[] state = new long[4];
    private long[] count = new long[2];
    private byte[] buffer = new byte[64];
    public String digestHexStr;
    private byte[] digest = new byte[16];

    public String getMD5ofStr(String inbuf) {
        this.md5Init();
        this.md5Update(inbuf.getBytes(), inbuf.length());
        this.md5Final();
        this.digestHexStr = "";

        for (int i = 0; i < 16; ++i) {
            this.digestHexStr = this.digestHexStr + byteHEX(this.digest[i]);
        }

        return this.digestHexStr;
    }

    public MD5() {
        this.md5Init();
    }

    private void md5Init() {
        this.count[0] = 0L;
        this.count[1] = 0L;
        this.state[0] = 1732584193L;
        this.state[1] = 4023233417L;
        this.state[2] = 2562383102L;
        this.state[3] = 271733878L;
    }

    private long F(long x, long y, long z) {
        return x & y | ~x & z;
    }

    private long G(long x, long y, long z) {
        return x & z | y & ~z;
    }

    private long H(long x, long y, long z) {
        return x ^ y ^ z;
    }

    private long I(long x, long y, long z) {
        return y ^ (x | ~z);
    }

    private long FF(long a, long b, long c, long d, long x, long s, long ac) {
        a += this.F(b, c, d) + x + ac;
        a = (long) ((int) a << (int) s | (int) a >>> (int) (32L - s));
        a += b;
        return a;
    }

    private long GG(long a, long b, long c, long d, long x, long s, long ac) {
        a += this.G(b, c, d) + x + ac;
        a = (long) ((int) a << (int) s | (int) a >>> (int) (32L - s));
        a += b;
        return a;
    }

    private long HH(long a, long b, long c, long d, long x, long s, long ac) {
        a += this.H(b, c, d) + x + ac;
        a = (long) ((int) a << (int) s | (int) a >>> (int) (32L - s));
        a += b;
        return a;
    }

    private long II(long a, long b, long c, long d, long x, long s, long ac) {
        a += this.I(b, c, d) + x + ac;
        a = (long) ((int) a << (int) s | (int) a >>> (int) (32L - s));
        a += b;
        return a;
    }

    private void md5Update(byte[] inbuf, int inputLen) {
        byte[] block = new byte[64];
        int index = (int) (this.count[0] >>> 3) & 63;
        if ((this.count[0] += (long) (inputLen << 3)) < (long) (inputLen << 3)) {
            ++this.count[1];
        }

        this.count[1] += (long) (inputLen >>> 29);
        int partLen = 64 - index;
        int i;
        if (inputLen >= partLen) {
            this.md5Memcpy(this.buffer, inbuf, index, 0, partLen);
            this.md5Transform(this.buffer);

            for (i = partLen; i + 63 < inputLen; i += 64) {
                this.md5Memcpy(block, inbuf, 0, i, 64);
                this.md5Transform(block);
            }

            index = 0;
        } else {
            i = 0;
        }

        this.md5Memcpy(this.buffer, inbuf, index, i, inputLen - i);
    }

    private void md5Final() {
        byte[] bits = new byte[8];
        this.Encode(bits, this.count, 8);
        int index = (int) (this.count[0] >>> 3) & 63;
        int padLen = index < 56 ? 56 - index : 120 - index;
        this.md5Update(PADDING, padLen);
        this.md5Update(bits, 8);
        this.Encode(this.digest, this.state, 16);
    }

    private void md5Memcpy(byte[] output, byte[] input, int outpos, int inpos, int len) {
        for (int i = 0; i < len; ++i) {
            output[outpos + i] = input[inpos + i];
        }

    }

    private void md5Transform(byte[] block) {
        long a = this.state[0];
        long b = this.state[1];
        long c = this.state[2];
        long d = this.state[3];
        long[] x = new long[16];
        this.Decode(x, block, 64);
        a = this.FF(a, b, c, d, x[0], 7L, 3614090360L);
        d = this.FF(d, a, b, c, x[1], 12L, 3905402710L);
        c = this.FF(c, d, a, b, x[2], 17L, 606105819L);
        b = this.FF(b, c, d, a, x[3], 22L, 3250441966L);
        a = this.FF(a, b, c, d, x[4], 7L, 4118548399L);
        d = this.FF(d, a, b, c, x[5], 12L, 1200080426L);
        c = this.FF(c, d, a, b, x[6], 17L, 2821735955L);
        b = this.FF(b, c, d, a, x[7], 22L, 4249261313L);
        a = this.FF(a, b, c, d, x[8], 7L, 1770035416L);
        d = this.FF(d, a, b, c, x[9], 12L, 2336552879L);
        c = this.FF(c, d, a, b, x[10], 17L, 4294925233L);
        b = this.FF(b, c, d, a, x[11], 22L, 2304563134L);
        a = this.FF(a, b, c, d, x[12], 7L, 1804603682L);
        d = this.FF(d, a, b, c, x[13], 12L, 4254626195L);
        c = this.FF(c, d, a, b, x[14], 17L, 2792965006L);
        b = this.FF(b, c, d, a, x[15], 22L, 1236535329L);
        a = this.GG(a, b, c, d, x[1], 5L, 4129170786L);
        d = this.GG(d, a, b, c, x[6], 9L, 3225465664L);
        c = this.GG(c, d, a, b, x[11], 14L, 643717713L);
        b = this.GG(b, c, d, a, x[0], 20L, 3921069994L);
        a = this.GG(a, b, c, d, x[5], 5L, 3593408605L);
        d = this.GG(d, a, b, c, x[10], 9L, 38016083L);
        c = this.GG(c, d, a, b, x[15], 14L, 3634488961L);
        b = this.GG(b, c, d, a, x[4], 20L, 3889429448L);
        a = this.GG(a, b, c, d, x[9], 5L, 568446438L);
        d = this.GG(d, a, b, c, x[14], 9L, 3275163606L);
        c = this.GG(c, d, a, b, x[3], 14L, 4107603335L);
        b = this.GG(b, c, d, a, x[8], 20L, 1163531501L);
        a = this.GG(a, b, c, d, x[13], 5L, 2850285829L);
        d = this.GG(d, a, b, c, x[2], 9L, 4243563512L);
        c = this.GG(c, d, a, b, x[7], 14L, 1735328473L);
        b = this.GG(b, c, d, a, x[12], 20L, 2368359562L);
        a = this.HH(a, b, c, d, x[5], 4L, 4294588738L);
        d = this.HH(d, a, b, c, x[8], 11L, 2272392833L);
        c = this.HH(c, d, a, b, x[11], 16L, 1839030562L);
        b = this.HH(b, c, d, a, x[14], 23L, 4259657740L);
        a = this.HH(a, b, c, d, x[1], 4L, 2763975236L);
        d = this.HH(d, a, b, c, x[4], 11L, 1272893353L);
        c = this.HH(c, d, a, b, x[7], 16L, 4139469664L);
        b = this.HH(b, c, d, a, x[10], 23L, 3200236656L);
        a = this.HH(a, b, c, d, x[13], 4L, 681279174L);
        d = this.HH(d, a, b, c, x[0], 11L, 3936430074L);
        c = this.HH(c, d, a, b, x[3], 16L, 3572445317L);
        b = this.HH(b, c, d, a, x[6], 23L, 76029189L);
        a = this.HH(a, b, c, d, x[9], 4L, 3654602809L);
        d = this.HH(d, a, b, c, x[12], 11L, 3873151461L);
        c = this.HH(c, d, a, b, x[15], 16L, 530742520L);
        b = this.HH(b, c, d, a, x[2], 23L, 3299628645L);
        a = this.II(a, b, c, d, x[0], 6L, 4096336452L);
        d = this.II(d, a, b, c, x[7], 10L, 1126891415L);
        c = this.II(c, d, a, b, x[14], 15L, 2878612391L);
        b = this.II(b, c, d, a, x[5], 21L, 4237533241L);
        a = this.II(a, b, c, d, x[12], 6L, 1700485571L);
        d = this.II(d, a, b, c, x[3], 10L, 2399980690L);
        c = this.II(c, d, a, b, x[10], 15L, 4293915773L);
        b = this.II(b, c, d, a, x[1], 21L, 2240044497L);
        a = this.II(a, b, c, d, x[8], 6L, 1873313359L);
        d = this.II(d, a, b, c, x[15], 10L, 4264355552L);
        c = this.II(c, d, a, b, x[6], 15L, 2734768916L);
        b = this.II(b, c, d, a, x[13], 21L, 1309151649L);
        a = this.II(a, b, c, d, x[4], 6L, 4149444226L);
        d = this.II(d, a, b, c, x[11], 10L, 3174756917L);
        c = this.II(c, d, a, b, x[2], 15L, 718787259L);
        b = this.II(b, c, d, a, x[9], 21L, 3951481745L);
        this.state[0] += a;
        this.state[1] += b;
        this.state[2] += c;
        this.state[3] += d;
    }

    private void Encode(byte[] output, long[] input, int len) {
        int i = 0;

        for (int j = 0; j < len; j += 4) {
            output[j] = (byte) ((int) (input[i] & 255L));
            output[j + 1] = (byte) ((int) (input[i] >>> 8 & 255L));
            output[j + 2] = (byte) ((int) (input[i] >>> 16 & 255L));
            output[j + 3] = (byte) ((int) (input[i] >>> 24 & 255L));
            ++i;
        }

    }

    private void Decode(long[] output, byte[] input, int len) {
        int i = 0;

        for (int j = 0; j < len; j += 4) {
            output[i] = b2iu(input[j]) | b2iu(input[j + 1]) << 8 | b2iu(input[j + 2]) << 16 | b2iu(input[j + 3]) << 24;
            ++i;
        }

    }

    public static long b2iu(byte b) {
        return (long) (b < 0 ? b & 255 : b);
    }

    public static String byteHEX(byte ib) {
        char[] Digit = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] ob = new char[]{Digit[ib >>> 4 & 15], Digit[ib & 15]};
        String s = new String(ob);
        return s;
    }

    public String getKeyEd(String Txt, String encrypt_key) {
        if (Txt.length() == 0 || Txt == null) {
            Txt = "";
        }

    encrypt_key = this.getMD5ofStr(encrypt_key);
        int m = 0;
        int n = encrypt_key.length();
        StringBuffer temp = new StringBuffer();

        for (int i = 0; i < Txt.length(); ++i) {
            if (m == n) {
                m = 0;
            }

            char cc1 = encrypt_key.charAt(m);
            //十六进制转为二进制进行异或计算
            char a = Txt.charAt(i);
            int cc4 = cc1 ^ a;
            char cc2 = (char) cc4;
            temp.append(cc2);
            ++m;
        }

        return String.valueOf(temp.toString());
    }

    public String encrypt(String Txt, String Key1) {
        int number = this.getRandom();
        String encrypt_key = this.getMD5ofStr(Integer.toString(number));
        int ctr = 0;
        int num = Txt.length();
        int num1 = encrypt_key.length();
        Txt = Txt.trim();
        StringBuffer temp = new StringBuffer();

        for (int er = 0; er < num; ++er) {
            if (ctr == num1) {
                ctr = 0;
            }

            char retur = encrypt_key.charAt(ctr);
            temp.append(retur);
            char cc2 = Txt.charAt(er);
            int cc4 = retur ^ cc2;
            char cc3 = (char) cc4;
            temp.append(cc3);
            ++ctr;
        }

        String var15 = temp.toString();
        String var14 = this.getKeyEd(var15, Key1);
        var14 = this.setFromBASE64(var14);
        var14 = var14.replaceAll("\n", "");
        var14 = var14.replaceAll("\r", "");
        var14 = var14.replaceAll("<br>", "");
        return var14;
    }

    public String decrypt(String Txt, String Key1) {
        Txt = this.getFromBASE64(Txt);
        Txt = this.getKeyEd(Txt, Key1);
        StringBuffer temp = new StringBuffer();
        boolean temp1 = false;

        for (int i = 0; i < Txt.length(); ++i) {
            char var9 = Txt.charAt(i);
            ++i;
            char cc1 = Txt.charAt(i);
            //异或运算
            int cc2 = cc1 ^ var9;
            char cc3 = (char) cc2;
            temp = temp.append(cc3);
        }

        Txt = temp.toString();
        return Txt;
    }

    public String getFromBASE64(String s) {
        if (s != null && s.length() > 0) {
            BASE64Decoder decoder = new BASE64Decoder();

            try {
                byte[] e = decoder.decodeBuffer(s);
                return new String(e);
            } catch (Exception var4) {
                return "";
            }
        } else {
            return "";
        }
    }

    public String setFromBASE64(String s) {
        if (s != null && s.length() > 0) {
            BASE64Encoder encoder = new BASE64Encoder();

            try {
                String e = encoder.encode(s.getBytes());
                return e;
            } catch (Exception var4) {
                return "";
            }
        } else {
            return "";
        }
    }

    public int getRandom() {
        Random generator = new Random();
        int limit = 320000;
        int randomNub = 1;
        boolean j = true;

        while (j) {
            randomNub = (int) (generator.nextDouble() * (double) limit);
            if (randomNub > 10) {
                j = false;
            }
        }

        return randomNub;
    }

    public static void main(String[] args) {
//        new MD5();
//        String pwd = "cGsFaXJ5CQQDN3AwAjEBNXFGfD8HMg==";
//        String p = MD5Util.md5decode(pwd);
//        System.out.println("解密后的密码是:" + p);
//        String en1 = MD5Util.md5encode("1");
//        System.out.println("加密密后的密文是:" + en1);
        String p = MD5Util.md5decode("AUc=");
        System.out.println("解密后的密码是:" + p);
    }
}

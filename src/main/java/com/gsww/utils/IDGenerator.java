package com.gsww.utils;


//import oracle.jdbc.driver.OracleDriver;

/**
 *Ψһ�������
 * 
 * @author gaga
 *@version 1.0
 */
public class IDGenerator {
	String[] formatStr;
	String linkChar;

	protected IDGenerator() {
	}

	/**
	 *������,UA��ʾ��д��ĸ,LA��ʾСд��ĸ,A����������ĸ.N��ʾ����
	 * 
	 * @param formatStr
	 *            []
	 *            ��ʽ�ַ�����,����"{A3,N3}"�ĺ�����ǰ����������ĸ��Ϊ��λ,����������
	 *            ��ֳ�Ϊ��λ�м���"-"����.
	 */
	public IDGenerator(String formatStr[], String linkChar) throws Exception {
		// ���formatStr�ĺϷ���

		for (int i = 0; i < formatStr.length; i++) {
			if (!(formatStr[i].indexOf("UA") != -1
					|| formatStr[i].indexOf("LA") != -1
					|| formatStr[i].indexOf("N") != -1
					|| formatStr[i].indexOf("A") != -1 || formatStr[i]
					.indexOf("AN") != -1))
				throw new Exception("formatStr�����д���,���������ĵ�.");
		}

		this.formatStr = formatStr;
		this.linkChar = linkChar;
	}

	/**
	 *���ID��
	 * 
	 * @return ���Ҫ���ID��
	 */
	public String getID() {
		return generateID();
	}

	/**
	 *���ID�ų���
	 * 
	 * @return ID�ų���
	 */
	public int getIDLength() {
		int len = 0;
		String str = "";

		for (int i = 0; i < formatStr.length; i++) {
			str = formatStr[i];

			if (str.indexOf("UA") != -1) {

				// ��д��ĸ
				len = len
						+ Integer.valueOf(str.substring(2, str.length()))
								.intValue();

			} else if (str.indexOf("LA") != -1) {
				// Сд��ĸ

				len = Integer.valueOf(str.substring(2, str.length()))
						.intValue();

			} else if (str.indexOf("AN") != -1) {
				// ��ĸ���ֻ��

				len = len
						+ Integer.valueOf(str.substring(2, str.length()))
								.intValue();

			} else if (str.indexOf("N") != -1) {
				// ����
				len = len
						+ Integer.valueOf(str.substring(1, str.length()))
								.intValue();

			} else if (str.indexOf("A") != -1) {
				// ��Сд���

				len = len
						+ Integer.valueOf(str.substring(1, str.length()))
								.intValue();

			}// if

		}// for

		len = linkChar.length() * (formatStr.length - 1) + len;

		return len;
	}

	// ����ID��
	protected String generateID() {
		String str = null;
		StringBuffer result = new StringBuffer("");

		for (int i = 0; i < formatStr.length; i++) {
			str = formatStr[i];

			if (str.indexOf("UA") != -1) {

				// ��д��ĸ

				int len = Integer.valueOf(str.substring(2, str.length()))
						.intValue();

				int num = 0;
				for (int j = 0; j < len; j++) {
					num = (int) Math.floor((Math.random() * 26)) + 'A';
					result.append((char) num);
				}

			} else if (str.indexOf("LA") != -1) {
				// Сд��ĸ

				int len = Integer.valueOf(str.substring(2, str.length()))
						.intValue();

				int num = 0;
				for (int j = 0; j < len; j++) {
					num = (int) Math.floor((Math.random() * 26)) + 'a';
					result.append((char) num);
				}

			} else if (str.indexOf("AN") != -1) {
				// ��ĸ���ֻ��

				int len = Integer.valueOf(str.substring(2, str.length()))
						.intValue();

				int num = 0;
				int select = 0;
				for (int j = 0; j < len; j++) {

					select = (int) Math.floor((Math.random() * 2));

					if (select == 0)
						num = (int) Math.floor((Math.random() * 26)) + 'a';
					else if (select == 1)
						num = (int) Math.floor((Math.random() * 26)) + 'A';
					else
						num = (int) (Math.random() * 9) + '0';

					result.append((char) num);
				}// for

			} else if (str.indexOf("N") != -1) {
				// ����
				int len = Integer.valueOf(str.substring(1, str.length()))
						.intValue();

				int num = 0;
				for (int j = 0; j < len; j++) {

					num = (int) (Math.random() * 9) + '0';
					result.append((char) num);
				}

			} else if (str.indexOf("A") != -1) {
				// ��Сд���

				int len = Integer.valueOf(str.substring(1, str.length()))
						.intValue();

				int num = 0;
				int select = 0;
				for (int j = 0; j < len; j++) {

					select = (int) Math.floor((Math.random() * 2));

					if (select == 0)
						num = (int) Math.floor((Math.random() * 26)) + 'a';
					else
						num = (int) Math.floor((Math.random() * 26)) + 'A';

					result.append((char) num);
				}

			}// if

			result.append(linkChar);

		}// for

		String tmpString = result.toString().substring(0,
				result.toString().length() - linkChar.length());

		return tmpString;
	}

	public static void main(String args[]) {
		String format[] = new String[] { "N40", "UA4", "N4", "AN4" };

		try {
			IDGenerator idg = new IDGenerator(format, "-");

			// Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection
			// conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","gaga","gaga");

			// Statement stm=conn.createStatement();
			// String sqlstr="";

			// long i=0;
			// while(true)
			// {
			// sqlstr="INSERT INTO id VALUES('" + + "')";
			// stm.executeUpdate(sqlstr);
			System.out.println(idg.getID());
			// }

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

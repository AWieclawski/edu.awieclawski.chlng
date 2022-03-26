package edu.string.references;

public class PlayString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String one = "text";
		String two = "text";

		String bbb;
		String aaa = bbb = "get";

		System.out.println(String
				.format(" one=%s, two=%s, (one.equals(two))=%s", one, two, one.equals(two)));
		System.out.println(String
				.format(" one=%s, two=%s, (one == two)=%s ", one, two, (one == two)));
		System.out.println(String
				.format(" aaa=%s, bbb=%s, (aaa.equals(bbb))=%s ", aaa, bbb, aaa.equals(bbb)));
		System.out.println(String
				.format(" aaa=%s, bbb=%s, (aaa == bbb)=%s ", aaa, bbb, (aaa == bbb)));

		aaa = "post";

		System.out.println(String
				.format(" aaa=%s, bbb=%s, (aaa.equals(bbb))=%s ", aaa, bbb, aaa.equals(bbb)));
		System.out.println(String
				.format(" aaa=%s, bbb=%s, (aaa == bbb)=%s ", aaa, bbb, (aaa == bbb)));

		bbb = "text";

		System.out.println(String
				.format(" one=%s, bbb=%s, (one.equals(bbb))=%s ", one, bbb, one.equals(bbb)));
		System.out.println(String
				.format(" one=%s, bbb=%s, (one == bbb)=%s ", one, bbb, (one == bbb)));
	}

}

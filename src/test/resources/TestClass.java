

public class TestClass {

	private static final int SOME_CONSTANT = 3;
	private static final boolean SOMEBOOLEAN = false;

	private int a;
	private int b;
	private int c;
	private final int d = 3;

	public String strA;
	public String strB;
	public String strC;
	public String str_d;

	TestClass() {}

	public void testMethod1() {}
	public void testMethod2(String arg, String arg2) {}
	public void testMethod3(String arg, String arg2) {}

	public void testMethod4(int a_var, boolean SOME_OTHER_VAR) {
		int a_local;
		int a2_local;
		int a_variable_name;
		int another_variable_name;
		final int secondStaticVariable;
	}
}
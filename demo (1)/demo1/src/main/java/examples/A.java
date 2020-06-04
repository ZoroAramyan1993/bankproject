package examples;

 class A1 {

}
class Test2 {
    static int N(Integer x) {
        return x;
    }
}

 class Test {
    static int M(Integer v){
        return v;
    }
    public static void main(String[] args){
        Integer x = M(100);     // 1
        //Integer z = N(100);     // 2
       // System.out.println(x + " " + z);
    }
}


 class IfElseTest {
    public static void main(String...args) {
        boolean b = false;
      //  if (b == false)
            if (b = false)
                System.out.println("if statement");
            else
                System.out.println("else statement");
    }
}


class Arrays{
    public static void main(String[] args) {
        int[]x = new int[25];
        System.out.println(x.length);
        System.out.println(x[24]==0);
       // System.out.println(x[25]==0);
        System.out.println(x[0]==0);
    }
}


 class TypesTutorial {
    public static void main(String... args) {
        A alpha = new B(0);
    }
}

class A {

//    public A(){
//
//    }
    A(int x){            // - 1 -
        a(x);            // - 2 -
    }

    void a(int x) {
        System.out.println("A-a: " + x);
    }
}

class B extends A {
    B(int x) {
        super(x);// - 3 -
        a(x);            // - 4 -
    }

    void a(int x) {
        System.out.println("B-a: " + x);
    }
}
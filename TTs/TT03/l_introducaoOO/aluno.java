class Ponto{
    private double x;
    private double y;
    private int id;
    static private int nextId = 0;

    /* CONSTRUTORES */
    public Ponto(){
        this.x = 0;
        this.y = 0;
        this.id = nextId;
        this.nextId += 1;
    }

    public Ponto(double x, double y){
        this.x = x;
        this.y = y;
        this.id = nextId;
        this.nextId += 1;
    }

    /* SETTERS */
    public void setX(double x){ this.x = x; }
    public void setY(double y){ this.y = y; }

    /* GETTERS */
    public double getX(){ return x; }
    public double getY(){ return y; }
    public int getID(){ return id; }
    public static int getNextID(){ return nextId; }

    /* MÉTODOS QUE CÁLCULAM A DISTÂNCIA ENTRE DOIS PONTOS */
    //entrada do tipo Ponto
    public double dist(Ponto p2){ return Math.sqrt(Math.pow((getX() - p2.getX()), 2) + Math.pow((getY() - p2.getY()), 2)); }
    //entrada do tipo (x,y)
    public double dist(double x, double y){ return Math.sqrt(Math.pow((getX() - x), 2) + Math.pow((getY() - y), 2)); }
    //entrada do tipo (x1,y1) e (x2,y2)
    public static double dist(double x1, double x2, double y1, double y2){ return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)); }

    /* METODO QUE DEFINE SE É TRIÂNGULO OU NÃO */
    public static String isTriangulo(Ponto p1, Ponto p2, Ponto p3){
        if (p1.dist(p2) + p2.dist(p3) == p3.dist(p1)) {
            return "NAO";
        } else return "SIM";
    }

    /* METODO QUE RETORNA ÁREA DE UM RETÂNGULO */
    public double getAreaRetangulo(Ponto p2){ return (getX() + getY()) * (p2.getX() + p2.getY()); }
}

public class aluno {
    
    public static void main(String[] args){
        Ponto p1 = new Ponto(4, 3);
        Ponto p2 = new Ponto(8, 5);
        Ponto p3 = new Ponto(9.2, 10);

        System.out.println("Distancia p1 entre e p2: " + p1.dist(p2));
        System.out.println("Distancia p1 entre e (5,2): " + p1.dist(5, 2));
        System.out.println("Distancia (4,3) entre e (5,2): " + Ponto.dist(4, 3, 5, 2));
        System.out.println("P1, P2, P3 sao triangulo:" + Ponto.isTriangulo(p1, p2, p3));
        System.out.println("Area retangulo:" + p1.getAreaRetangulo(p2));
        System.out.println("ID de P1: " + p1.getID());
        System.out.println("ID de P2: " + p2.getID());
        System.out.println("ID de P3: " + p3.getID());
        System.out.println("Next ID: " + Ponto.getNextID());
    }
}

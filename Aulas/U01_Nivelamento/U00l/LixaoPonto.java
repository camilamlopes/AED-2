
/**
 * Introducao a programacao orientada por objetos
 * @author Camila Moreira Lopes
 * @version 1 02/2022
 */
import java.lang.Math;
class Ponto {
    //Declaracao de atributos privados
    private double x, y;
    private int id;
    private static int nextID = 0;

    //Construtor vazio
    public Ponto(){
        this.x = 0;
        this.y = 0;
        this.id = nextID;
        nextID += 1;
    }

    //Construtor que recebe parametros
    public Ponto(double x, double y){
        this.x = x;
        this.y = y;
        this.id = nextID;
        nextID += 1;
    }

    // Métodos get
    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public int getID(){
        return this.id;
    }

    public static int getNextID(){
        return nextID;
    }

    // Métodos set
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    // Método para compilar classe LixaoPonto
    public double dist(Ponto p){
        double base = p.x - x;
        double altura = p.y - y;
        return Math.sqrt(base * base + altura * altura);
    }

    public double dist(double x2, double y2){
        double base = x - x2;
        double altura = y - y2;
        return Math.sqrt(base * base + altura * altura);
    }

    public static double dist(double x1, double y1, double x2, double y2){
        double base = x1 - x2;
        double altura = y1 - y2;
        return Math.sqrt(base * base + altura * altura);
    }

    public static boolean isTriangulo (Ponto p1, Ponto p2, Ponto p3){
        double a = p1.dist(p2),
               b = p2.dist(p3),
               c = p3.dist(p1);
        if(a + b < c || a + c < b || b + c < a){
            return false;
        } else{
            return true;
        }
    }
    public double getAreaRetangulo(Ponto p){
        return (p.x - x) * (p.y - y);
    }
}

class LixaoPonto {
    public static void main (String[] args){
        Ponto p1 = new Ponto(4,3);
        Ponto p2 = new Ponto(8,5);
        Ponto p3 = new Ponto(9.2,10);
        System.out.println("Distancia p1 entre e p2: " + p1.dist(p2));
        System.out.println("Distancia p1 entre e (5,2): " + p1.dist(5,2));
        System.out.println("Distancia (4,3) entre e (5,2): " + Ponto.dist(4,3,5,2));
        System.out.println("P1, P2, P3 sao triangulo:" + Ponto.isTriangulo(p1,p2,p3));
        System.out.println("Area retangulo:" + p1.getAreaRetangulo(p2));
        System.out.println("ID de P1: " + p1.getID());
        System.out.println("ID de P2: " + p2.getID());
        System.out.println("ID de P3: " + p3.getID());
        System.out.println("Next ID: " + Ponto.getNextID());
    }
}
import java.util.*;
class retangulo{
    //variáveis privadas
    private double base;
    private double altura;

    //contrutor
    public retangulo(){
        this.base = 0;
        this.altura = 0;
    }

    public retangulo(double a, double b){
        this.altura = a;
        this.base = b;
    }

    /* SETTERS */
    public void setBase(double b){ this.base = b; }
    public void setAltura(double a){ this.altura = a; }
    
    /* GETTERS */
    public double getBase(){ return base; }
    public double getAltura(){ return altura; }

    //método que retorna área do retângulo
    public double getArea(){ return (getBase() * getAltura()); }

    //método que retorna o perímetro do retângulo
    public double getPerimetro(){ return 2 * (getBase() + getAltura()); }

    //método que retorna a diagonal do retângulo
    public double getDiagonal(){ return Math.sqrt(Math.pow(getAltura(), 2) + Math.pow(getBase(), 2)); }

    // leitor do teclado
    public void in() {
        Scanner input = new Scanner(System.in);
        setBase(input.nextDouble());
        setAltura(input.nextDouble());
    }

    //escreve todos os dados
    public void printAll() {
        System.out.println("base = " + getBase());
        System.out.println("altura = " + getAltura());
        System.out.println("perimetro = " + getPerimetro());
        System.out.println("area = " + getArea());
        System.out.println("diagonal = " + getDiagonal());

    }
}

/* CLASSE LIXÃO */
public class lixao{
    public static void main(String[] args){
        retangulo ret = new retangulo();
        retangulo ret2 = new retangulo();

        ret.in();
        ret.printAll();
        ret2.in();
        ret2.printAll();
        
    }
}
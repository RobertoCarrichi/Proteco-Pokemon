public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int max = 150;
            int min = 50;
            int range = max - min + 1;
            int normal = (int)((Math.random() * range) + min);
            int extra = (int)(Math.random()*20);
            
            double aux = Math.random()*2; 
            System.out.println(aux);

            int resultado;
            if (aux>1){
                System.out.println("SERÁ POSITIVO");
                resultado = normal+extra;
            } else {
                System.out.println("SERÁ NEGATIVO");
                resultado = normal-extra;
            }

            System.out.println(normal);
            System.out.println(extra);
            System.out.println("Final: "+resultado);
            
        }
    }
}

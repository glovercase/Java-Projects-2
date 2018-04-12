
public class main{
    double a = 1;
    double b = 2;
    double sum = 0;
    double result = 2;
    public main(){
       while(sum < 4000000){
           if(sum % 2 == 0){
               result += sum;
            }
           sum = a + b;
           a = b;
           b = sum;
           
        }
    System.out.println(result);
}
}
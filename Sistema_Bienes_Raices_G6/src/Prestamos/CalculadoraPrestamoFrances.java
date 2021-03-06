
package Prestamos;

//@see
import java.math.BigDecimal;

/**
 *
 * @author ALICE
 */

/*==============================================================================|
SubCLASE CalculadoraPrestamoFrances                                             |
Calcula las cuotas mensuales del préstamo de tipo francés.                      |
================================================================================*/

public class CalculadoraPrestamoFrances extends CalculadoraPrestamo{
    private double tasaInteres;
    private double costoPropiedad;
    private int num_cuotas;
    
    /**Constructor con 3 parametros
     * @param tasaInteres
     * @param costoPropiedad
     * @param num_cuotas 
     */
    public CalculadoraPrestamoFrances(double tasaInteres, double costoPropiedad, int num_cuotas){
        this.tasaInteres= tasaInteres;
        this.costoPropiedad= costoPropiedad;
        this.num_cuotas=num_cuotas;
    }
    
    //Metodo Abstracto Sobrescritura siguiendo los calculos para Amortizacion Frances
    /**
     * calculadoraPrestamo()
     * @return valor a pagar usando sistema frances
     */
    @Override
    public double calculadoraPrestamo(){
        
        /*= FORMULA PARA AMORTIZACION FRANCESA
            (A1*(1+A1)^B1)*C1/(((1+A1)^B1)-1)
            A1= Tasa de interes efectivo
            TasaInteresEfectivo = ((1+ tasaInteres)^(1/12))-1
            B1= Numero total de pagos meses
            C1= Valor Capital de prestamo*/
        
        double tasaParcial = (1+tasaInteres/100);
        double pot =Math.pow(12,-1);
        double tasaInteresEfectivo =Math.pow(tasaParcial,pot)-1; //A1
        double tasaComp=Math.pow(tasaInteresEfectivo+1,num_cuotas); //(1+A1)^B1
        
        double cuotaMensual= ((tasaInteresEfectivo*tasaComp)*costoPropiedad)/(tasaComp-1);
        System.out.println(cuotaMensual);
        double rnd = Math.round(cuotaMensual*100.0)/100.0;
        double interesMensual;//(Capital Adeudado*tasaInteresEfectivo)/100
        double amortizacion;//cuotaMensual-interesMensual;
        double capitalAmortizado=0;//CapitalAmortizado+Amortuzacion
        double capitalVivo=costoPropiedad;
        
        System.out.println("\nCuota\tCuotaMonto      InteresMonto\tAMORTIZACION\tCapital Vivo\tCapital AMORTIZADO");
        for(int i=1;i<=num_cuotas;i++){
            interesMensual = (capitalVivo*(tasaInteresEfectivo))/100;
            double rndInteresMensual = Math.round(interesMensual*100.0)/100.0;
            amortizacion = cuotaMensual-interesMensual;
            double rndAmort=Math.round(amortizacion*100.0)/100.0;
            capitalAmortizado += amortizacion;
            double rndCapital = Math.round(capitalAmortizado*100.0)/100.0;
            capitalVivo=capitalVivo-amortizacion;
            double rndCapVivo = Math.round(capitalVivo*100.0)/100.0;
            
           System.out.println(i+"\t"+rnd+"\t\t"+rndInteresMensual+"\t\t"+rndAmort+"            "+rndCapVivo+"            "+rndCapital);
        }
        
        System.out.println();
        
        return cuotaMensual;   
    }
    
    /**
     * getTasa()
     * @return tasa de interes
     */
    public double getTasa(){
        return tasaInteres;
    }
    
    /**
     * getCosto()
     * @return costo de propiedad o prestamo
     */
    public double getCosto(){
        return costoPropiedad;
    }
    
    /**
     * getCuotas()
     * @return numero de cuotas a pagar
     */
    public double getCuotas(){
        return num_cuotas;
    }
        
}

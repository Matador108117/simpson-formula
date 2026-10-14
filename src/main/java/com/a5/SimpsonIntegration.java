package com.a5;
import java.util.List;
import java.util.ArrayList;
public class SimpsonIntegration {
    int numSeg;
    double dblL;
    double dblH;
    double dof;
    List<Double> dblX ;
    double secmentsWidth;
    double [] gamma = new double[2];
    double c;
    List<Double> studentArray;
    double simsonresult;
    public double calculateSegmentheight(){
       this.secmentsWidth = (dblH - dblL)/numSeg;
        return this.secmentsWidth;
    }
    SimpsonIntegration(double limL, double limH, double dof, int numSeg){
        reset();
        this.dof = dof;
        this.dblL = limL;
        this.dblH = limH;
        this.numSeg = numSeg;



    }
    public double Gamma(double z) {
    double result = 1.0;
    while (z > 1.0) {
        z -= 1.0;
        result *= z;
    }
    if (Math.abs(z - 0.5) < 1e-10) {
        result *= Math.sqrt(Math.PI);
    }
    return result;
}
    public double calculateC(){
           gamma[0] = Gamma((dof + 1.0) / 2.0);
           gamma[1] = Gamma(dof / 2.0);
           c = gamma[0] / (Math.sqrt(dof * Math.PI) * gamma[1]);
        return c;
    }
    public List<Double> calculateStudent(){
        int i = 0;
        this.studentArray.add(0.0);
        while (i < this.numSeg) {
            this.studentArray.add(c * Math.pow((1 + ((this.studentArray.get(i) * this.studentArray.get(i)) / 9)), -5.0));
            i++;
        }
        return studentArray;
    }
    public double calcualteSimpson(){
        int i = 0;
        for (double num : this.studentArray) {
            if(i == 0){
                this.simsonresult += 1*num;
            }
            if (num == this.studentArray.size()){
                this.simsonresult += 1*num;
            }
            if(num % 2 == 0){
            this.simsonresult += (2 * num);
            }

            if (num % 2 != 0){
                this.simsonresult += (4 * num);
            }
            i++;
        }
        return this.simsonresult;
    }
    public double calculatesimpsonIntegretion(){
        this.secmentsWidth  = calculateSegmentheight();
        this.c = calculateC();
        this.studentArray = calculateStudent();
        this.simsonresult = calcualteSimpson();
        return  simsonresult;
    }
    public void reset (){
        this.numSeg = 0;
        this.gamma[0] = 0.0;
        this.gamma[1] = 0.0;
        this.secmentsWidth = 0.0;
        this.c = 0.0;
        this.simsonresult = 0.0;
        this.studentArray = new ArrayList<Double>();
    }
    public double recalculate( int numSeg){
        this.reset();
        this.numSeg = numSeg;
        return calculatesimpsonIntegretion();

    }
    public int getNumSeg() {
        return numSeg;
    }
    public void setNumSeg(int numSeg) {
        this.numSeg = numSeg;
    }
    public double getDblL() {
        return dblL;
    }
    public void setDblL(double dblL) {
        this.dblL = dblL;
    }
    public double getDblH() {
        return dblH;
    }
    public void setDblH(double dblH) {
        this.dblH = dblH;
    }
    public double getDof() {
        return dof;
    }
    public void setDof(double dof) {
        this.dof = dof;
    }
    public List<Double> getDblX() {
        return dblX;
    }
    public void setDblX(List<Double> dblX) {
        this.dblX = dblX;
    }
    public double getSecmentsWidth() {
        return secmentsWidth;
    }
    public void setSecmentsWidth(double secmentsWidth) {
        this.secmentsWidth = secmentsWidth;
    }
    public double[] getGamma() {
        return gamma;
    }
    public void setGamma(double[] gamma) {
        this.gamma = gamma;
    }
    public double getC() {
        return c;
    }
    public void setC(double c) {
        this.c = c;
    }
    public List<Double> getStudentArray() {
        return studentArray;
    }
    public void setStudentArray(List<Double> studentArray) {
        this.studentArray = studentArray;
    }
    public double getSimsonresult() {
        return simsonresult;
    }
    public void setSimsonresult(double simsonresult) {
        this.simsonresult = simsonresult;
    }

    
    
    
    
    
}

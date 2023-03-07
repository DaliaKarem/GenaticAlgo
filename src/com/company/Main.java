package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static int counterOffsprings;
    public static float[][]choro={};
    public static String chromosome[] = {};
    public static int myTotalValue;
    public static int numOfSelected;
    public static ArrayList<Float>errors=new ArrayList<>();
    public static ArrayList<String> selectedItems=new ArrayList<>();
    public static ArrayList choromo[]={};
    public static ArrayList<Float>floats=new ArrayList<>();
    public static int t;
    public static int T;
    public static float totalError[]={};
    public static int myDegree;
    public static int num_chorom;
    public static float[][] myArr= {};
    public static ArrayList<ArrayList<Float>>Coefficients=new ArrayList<>();
    public static float read[][]={};
    public static int num_dataset;
    public static int numOfPoints;
    public static int deg;


    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static float RandomFloating(float min, float max)
    {    return (new Random().nextFloat() * (max - min)) + min;
    }
    public static float power(final float base, final int power) {
        float result = 1;
        for( int i = 0; i < power; i++ ) {
            result *= base;
        }
        return result;
    }
    public static float getRandomNumber(float min, float max) {
        return (float) ((Math.random() * (max - min)) + min);
    }

    public static void readFromFile(File file) {
        BufferedReader reader=null;
        try {
            reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void initialized_Chorom(int degree,float[][] arr, int Numchorom,int Num_points) {
        System.out.println("///////////////////////////INITIALIZED VALUES////////////////////////////////");
        t=0;
        T=Numchorom/2;
        myTotalValue=0;
        counterOffsprings=1;
        numOfSelected=0;
        num_chorom=Numchorom;
        myDegree=degree;
        chromosome= new String[Numchorom];
        selectedItems=new ArrayList<>();
        choro=new float[Numchorom][degree+1];
        choromo=new ArrayList[Numchorom];
        myArr=new float[Num_points][2];

        for (int i=0;i<Num_points;i++){
            myArr[i][0]=arr[i][0];
            myArr[i][1]=arr[i][1];
        }

        for (int i = 0; i < Numchorom; i++) {
            floats=new ArrayList<>();
            for (int j = 0; j <degree+1; j++) {
                chromosome[i] = "c" + (i + 1);
                floats.add(RandomFloating(-20,20));
                choro[i][j]=RandomFloating(-20,20);
            }
            choromo[i]=floats;
        }

        for (int i = 0; i < Numchorom; i++) {
            System.out.println(chromosome[i] +" -> "+choromo[i]);
        }


        fitness(Numchorom,Num_points,degree,chromosome,choro,arr);
    }


    public static void fitness(int Numchorom,int Num_points,int degree,String[] choromosome,float [][]choro,float arr[][]) {
        System.out.print("\n");
        System.out.println("///////////////////////////FITNESS////////////////////////////////");
        float []MeanSqu=new float[Numchorom];
        float []M=new float[degree+1];
        float result,r;
        totalError=new float[Numchorom];
        float sum = 0;
        for(int i=0;i<Numchorom;i++) {
            for (int j = 0; j < Num_points; j++)
            {
                for (int b=1;b<degree+1;b++)
                {  M[0]=choro[i][0];
                    M[b] =M[b-1]+(choro[i][b]*(power(arr[j][0],b)));
                }
                result=(M[degree-1]-arr[0][1]);
                MeanSqu[j]=power(result,2);
                sum+=MeanSqu[j];
            }
            r=sum/Numchorom;
            totalError[i]=(float) 1/r;
            sum=0;
        }

        for(int i=0;i<Numchorom;i++)
        {
            System.out.println(chromosome[i] + " "+totalError[i] );
        }
        Selection(choromosome,totalError,Numchorom);

    }



    public static void Selection(String[]chromosome,float[]totalerror,int Numchorom) {
        System.out.print("\n");
        //to calcu cummulative fitness
        float cummFitness[] = new float [Numchorom];
        String selecChoro[]=new String[Numchorom];
        String selecChoro2[]=new String[Numchorom];
        String finalecho[]=new String[(Numchorom/2)+1];
        cummFitness[0] = totalerror[0];

        for (int i = 1; i < Numchorom; i++) {
            cummFitness[i] = cummFitness[i - 1] + totalerror[i];

        }

        for (int i = 0; i < Numchorom; i++)
        {
            System.out.println(chromosome[i]+"  cummFitnessss  " + cummFitness[i]);
        }
        int I = 0;
        int Firstchoro[]=new int[Numchorom];
        int secchoro[]=new int [Numchorom];
        while ( I <= (Numchorom/ 2))
        {
            System.out.print("\n");
            System.out.println("///////////////////////////SELECTION////////////////////////////////");
            Firstchoro[I] = getRandomNumber(0, Numchorom);
            System.out.println("Random choro " + Firstchoro[I]);
            secchoro[I] = getRandomNumber(0, Numchorom);
            System.out.println("random Choro " + secchoro[I]);
            selecChoro[I] = chromosome[Firstchoro[I] ];
            selecChoro2[I] = chromosome[secchoro[I] ];
            I++;
            //System.out.println("IIIIIIII"+I);
        }

        for(int i=0;i<Numchorom/2;i++)
        {
            if(cummFitness[Firstchoro[i]]>cummFitness[secchoro[i]])
            {
                finalecho[i]=selecChoro[i];
            }
            else  if(cummFitness[Firstchoro[i]]<cummFitness[secchoro[i]]){
                finalecho[i]=selecChoro2[i];
            }
        }
        String result[]=new String[Numchorom/2];
//        for(int i=0;i<Numchorom/2;i++)
//        {
//            result[i]=finalecho[i];
//            result[i+1]=finalecho[i+1];
//            i++;
//            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrr"+result[i] +"    rrrrrr2"   +result[i-1]);
//
//        }
        crossOver(finalecho,Numchorom);
//for (int i=0;i<Numchorom/2;i++)
//{
//    System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssssssss       "+finalecho[i]);
//}


    }
    public static void crossOver(String[] nameOfChromosomes,int num){
        System.out.print("\n");
        System.out.println("///////////////////////////CROSSOVER////////////////////////////////");
        ArrayList<Float> values[]=new ArrayList[2];
        ArrayList<Float>myValues=new ArrayList<>();

        for (int i=0;i<chromosome.length;i++){
            if(nameOfChromosomes[0]==chromosome[i]){
                values[0]=choromo[i];
            }
            if(nameOfChromosomes[1]==chromosome[i]){
                values[1]=choromo[i];

            }
        }
//        for (int m=0;m<values[1].size();m++){
//            myValues.add(values[1].get(m));
//        }
        System.out.println("value 0->"+values[0]);
        System.out.println("value 1->"+values[1]);
        double Pc= 0.5;
        Random random= new Random();
        double r1=random.nextDouble();
        System.out.println("R1->"+r1);
        if(r1<=Pc) {
            //Do crossOver
            int r2 = random.nextInt(values[0].size());
            int r3=random.nextInt(values[0].size());
            if (r2 == 0) {
                while (true) {
                    r2 = random.nextInt(values[0].size());
                    if (r2 != 0) {        //no zero value
                        break;
                    }
                }
            }
            if (r3 == 0) {
                while (true) {
                    r3 = random.nextInt(values[0].size());
                    if (r3 != 0) {        //no zero value
                        break;
                    }
                }
            }
            System.out.println("R2->" + r2);
            System.out.println("R3->" + r3);

            ArrayList<Float> temp1 = new ArrayList<>();
            ArrayList<Float> temp2 = new ArrayList<>();
            int len = values[0].size();

            if(r2<r3){
                for (int i = 0; i < len; i++) {
                    if (i >= r2 && i<r3) {
                        temp1.add(values[0].get(i));

                    }
                }
                System.out.println("ttttttemp 1111111111111111       " +temp1);
                for (int i = 0; i < len; i++) {
                    if (i >= r2 && i<r3) {
                        temp2.add(values[1].get(i));
                    }
                }
                System.out.println("ttttttttttttttttttttttteemp 22222222222222222222       "+temp2);
                myValues=new ArrayList<>();
                for (int i = 0; i < len; i++) {
                    if (i >= r2 && i<r3) {
                        for (int j=0;j<temp2.size();j++){
                            myValues.add(temp2.get(j));
                            if(i!=r3-1)
                            {
                                i++;
                            }
                        }
                    }else {
                        myValues.add(values[0].get(i));
                    }
                }
                System.out.println("my valuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu       "+myValues);
                values[0]=myValues;
                myValues=new ArrayList<>();
                for (int i = 0; i < len; i++) {
                    if (i >= r2 && i<r3) {
                        for (int j=0;j<temp1.size();j++){
                            myValues.add(temp1.get(j));
                            if(i!=r3-1)
                            {
                                i++;
                            }
                        }
                    }else {
                        myValues.add(values[1].get(i));
                    }
                }
                System.out.println("my valuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu       "+myValues);
                values[1]=myValues;

            }else if(r3<r2){
                for (int i = 0; i < len; i++) {
                    if (i >= r3 && i<r2) {
                        temp1.add(values[0].get(i));

                    }
                }
                System.out.println("ttttttemp 1111111111111111       " +temp1);
                for (int i = 0; i < len; i++) {
                    if (i >= r3 && i<r2) {
                        temp2.add(values[1].get(i));
                    }
                }
                System.out.println("ttttttemp 1111111111111111222222222222222       " +temp2);
                myValues=new ArrayList<>();
                for (int i = 0; i < len; i++) {
                    if (i >= r3 && i<r2) {
                        for (int j=0;j<temp2.size();j++){
                            myValues.add(temp2.get(j));
                            if(i!=r2-1)
                            {
                                i++;
                            }

                        }

                    }else {
                        myValues.add(values[0].get(i));
                    }
                }
                System.out.println("my valuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu       "+myValues);
                values[0]=myValues;
                myValues=new ArrayList<>();
                for (int i = 0; i < len; i++) {
                    if (i >= r3 && i<r2) {
                        for (int j=0;j<temp1.size();j++){
                            myValues.add(temp1.get(j));
                            if(i!=r2-1)
                            {
                                i++;
                            }
                        }
                    }else {
                        myValues.add(values[1].get(i));
                    }
                }
                System.out.println("my valuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu       "+myValues);

                values[1]=myValues;

            }
            System.out.println("VALUES AFTER CROSSOVER:");
            System.out.println(nameOfChromosomes[0] + "->" + values[0]);
            System.out.println(nameOfChromosomes[1] + "->" + values[1]);
        }else {
            System.out.println("NO CROSSOVER");
        }
        mutation(nameOfChromosomes,values);
    }

    public static void mutation(String[] nameOfChromosomes,ArrayList<Float>[] values){
        System.out.print("\n");
        System.out.println("///////////////////////////MUTATION////////////////////////////////");
        ArrayList<Float>myValues1=new ArrayList<>();
        ArrayList<Float>myValues2=new ArrayList<>();

        //get LB&UB
        float LB1=values[0].get(0);
        for (int j=0;j<values[0].size();j++){
            if(values[0].get(j)<LB1){
                LB1=values[0].get(j);
            }
        }
        float UB1=values[0].get(0);
        for (int j=0;j<values[0].size();j++){
            if(values[0].get(j)>UB1){
                UB1=values[0].get(j);
            }
        }
        float LB2=values[1].get(0);
        for (int j=0;j<values[1].size();j++){
            if(values[1].get(j)<LB2){
                LB2=values[1].get(j);
            }
        }
        float UB2=values[1].get(0);
        for (int j=0;j<values[1].size();j++){
            if(values[1].get(j)>UB2){
                UB2=values[1].get(j);
            }
        }
        double Pm=0.5;
        Random random= new Random();
        double rm;
        for (int i=0;i<values[0].size();i++){
            System.out.println("Num Bit->"+i);
            rm=random.nextDouble();
            System.out.println("Rm->"+rm);
            if(rm<=Pm) {
                System.out.println("MUTATION FOR THE BIT "+i+" IN CHROMOSOME 1");
                float dL=values[0].get(i)-LB1;
                float dU=UB1-values[0].get(i);
                double r1=random.nextDouble();
                System.out.println("R1->"+r1);
                float y=0;
                if(r1<=0.5){
                    y=dL;
                }else if(r1>0.5){
                    y=dU;
                }
                double r=random.nextDouble();
                float b=(float) ((Math.random() * (5 - 0)) + 0);
                double pw1=Math.pow(1-(t/T), b);
                double pw2=Math.pow(r,pw1);
                float dt_y= (float) (y*(1-pw2));

                if(y==dL){
//                    System.out.println("vaallll"+(values[0].get(i)-dt_y));
                    myValues1.add((values[0].get(i)-dt_y));
                }else if(y==dU){
//                    System.out.println("vaallll"+(values[0].get(i)+dt_y));
                    myValues1.add((values[0].get(i)+dt_y));
                }
                System.out.println("----------------------------------");
            }else {
                myValues1.add(values[0].get(i));
                System.out.println("No MUTATION FOR THE BIT "+i+" IN CHROMOSOME 1");
                System.out.println("----------------------------------");
            }
            rm=random.nextDouble();
            System.out.println("Rm->"+rm);
            if(rm<=Pm) {
                System.out.println("MUTATION FOR THE BIT "+i+" IN CHROMOSOME 2");
                float dL=values[1].get(i)-LB2;
                float dU=UB2-values[1].get(i);
                double r1=random.nextDouble();
                float y=0;
                if(r1<=0.5){
                    y=dL;
                }else if(r1>0.5){
                    y=dU;
                }
                double r=random.nextDouble();
                float b=(float) ((Math.random() * (5 - 0)) + 0);
                double pw1=Math.pow(1-(t/T), b);
                double pw2=Math.pow(r,pw1);
                float dt_y= (float) (y*(1-pw2));

                if(y==dL){
//                    System.out.println("vaallll"+(values[1].get(i)-dt_y));
                    myValues2.add((values[1].get(i)-dt_y));
                }else if(y==dU){
//                    System.out.println("vaallll"+(values[1].get(i)+dt_y));
                    myValues2.add((values[1].get(i)+dt_y));
                }
                System.out.println("----------------------------------");
            }else {
                myValues2.add(values[1].get(i));
                System.out.println("No MUTATION FOR THE BIT "+i+" IN CHROMOSOME 2");
                System.out.println("----------------------------------");
            }
        }
        t++;
        values[0]=new ArrayList<>();
        values[1]=new ArrayList<>();
        values[0]=myValues1;
        values[1]=myValues2;
        System.out.println("VALUES AFTER MUTATION:");
        System.out.println(nameOfChromosomes[0]+"->"+myValues1);
        System.out.println(nameOfChromosomes[1]+"->"+myValues2);

        replacement(nameOfChromosomes,values);
    }

    public static float FitnessOffspring(ArrayList<Float>choro)
    {

        float MeanSqu[]=new float[numOfPoints];
        float M[]=new float[myDegree+1];
        float totalError=0;
        float result;
        float sum = 0;

        for (int j = 0; j < numOfPoints; j++)
        {
            for (int b=1;b<myDegree+1;b++)
            {  M[0]=choro.get(0);
                M[b] =M[b-1]+(choro.get(b)*(power(myArr[j][0],b)));
            }
            result=(M[myDegree-1]-myArr[0][1]);
            MeanSqu[j]=power(result,2);
            sum+=MeanSqu[j];
        }
        totalError=sum/numOfPoints;
        result=1/totalError;//result contains fitness for only one Choro
        return result;
    }
    public static void replacement(String[] nameOfChromosomes,ArrayList<Float>[] values) {
        //replacement part
        System.out.print("\n");
        System.out.println("///////////////////////////REPLACEMENT////////////////////////////////");
        float offspringFitness1=FitnessOffspring(values[0]);
        float offspringFitness2=FitnessOffspring(values[1]);
        float totalError1=0,totalError2=0;
        for (int i=0;i<chromosome.length;i++) {
            if (nameOfChromosomes[0] == chromosome[i]) {
                if(offspringFitness1<totalError[i]){
                    chromosome[i]="OS"+counterOffsprings;
                    nameOfChromosomes[0]="OS"+counterOffsprings;
                    totalError[i]=offspringFitness1;
                    totalError1=offspringFitness1;
                    counterOffsprings++;
                    choromo[i]=values[0];
                    System.out.println("OFFSPRING OF CHROMOSOME 1 IS THE BEST");
                    break;
                }else {
                    values[0]=new ArrayList<>();
                    values[0]=choromo[i];
                    totalError1=totalError[i];
                    System.out.println("Parent OF CHROMOSOME 1 IS THE BEST");
                }
            }
        }

        //Handle if the two chromosomes are the same
        int count=0;
        boolean status=false;
        for(int i=0;i<values[0].size();i++){
            if(values[0].get(i)==values[1].get(i)){
                count++;
            }
        }
        if(count==values[1].size()){
            status=true;
        }

        if ((nameOfChromosomes[0]!=nameOfChromosomes[1])||(nameOfChromosomes[0]==nameOfChromosomes[1] && !status)) {
            for (int i = 0; i < chromosome.length; i++) {
                if (nameOfChromosomes[1] == chromosome[i]) {
                    if (offspringFitness2 < totalError[i]) {
                        chromosome[i] = "OS" + counterOffsprings;
                        nameOfChromosomes[1] = "OS" + counterOffsprings;
                        totalError[i] = offspringFitness2;
                        totalError2 = offspringFitness2;
                        counterOffsprings++;
                        choromo[i]=values[1];
                        System.out.println("OFFSPRING OF CHROMOSOME 1 IS THE BEST");
                        break;
                    } else {
                        values[1]=new ArrayList<>();
                        values[1]=choromo[i];
                        totalError2 = totalError[i];
                        System.out.println("Parent OF CHROMOSOME 1 IS THE BEST");
                    }
                }
            }
        }else {
            values[1]=new ArrayList<>();
            values[1]=values[0];
            totalError2=totalError1;
        }

        selectedItems.add(nameOfChromosomes[0]);
        selectedItems.add(nameOfChromosomes[1]);
//        errors.add(totalError1);
//        errors.add(totalError2);
//        Coefficients.add(values[0]);
//        Coefficients.add(values[1]);

        System.out.print("\n");
        System.out.println("VALUES AFTER REPLACEMENT:");
        for (int i=0;i<chromosome.length;i++){
           errors.add(totalError[i]);
            System.out.println(chromosome[i]+"->Error: "+totalError[i]);
            Coefficients.add(choromo[i]);
            System.out.println("Coefficients->"+choromo[i]);
        }
    }

    public static void outPut(int numDataset,FileWriter writer){

        try {

            writer.write("\n");
            writer.write("///////////////////////////THE FINAL OUTPUT////////////////////////////////");
            writer.write("\nDATASET: "+numDataset+"\n");
            writer.write("\nSELECTED ITEMS: "+"\n");
            for (int i=0;i<chromosome.length;i++) {
                writer.write("\n"+chromosome[i] + "->ERROR: "+totalError[i]);
                writer.write("\nCoefficients->" + choromo[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("\n");
        System.out.println("///////////////////////////THE FINAL OUTPUT////////////////////////////////");
        System.out.println("DATASET: "+numDataset);
        System.out.println("SELECTED ITEMS: \n");
        for (int i=0;i<selectedItems.size();i++){
            System.out.print(selectedItems.get(i)+"->ERROR: ");
            System.out.println(errors.get(i));
            System.out.println("\nCoefficients->"+Coefficients.get(i));
        }

    }

    public static void main(String[] args) {
        // write your code here
        int count=0;
        Scanner sc= null;
        int numDataset=0;
        File file=new File("input.txt");
        File file2=new File("output.txt");
        FileWriter writer=null;
        try {
             writer=new FileWriter(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sc = new Scanner(file);
            sc.useLocale(Locale.US);
            while (sc.hasNext())
            {
                if (sc.hasNextFloat()) {
                    if(count==0){
                        num_dataset= (int) sc.nextFloat();
                        System.out.println("datasetNum:"+num_dataset);
                        count++;
                    }if(count==1){
                        numOfPoints=(int) sc.nextFloat();
                        System.out.println("numPoints:"+numOfPoints);
                        read=new float[numOfPoints][2];
                        count++;
                    }if (count==2){
                        deg=(int) sc.nextFloat();
                        System.out.println("degree:"+deg);
                        count++;
                    }
                    if (count==3){
                        for (int i=0;i<numOfPoints;i++){
                            read[i][0]=sc.nextFloat();
                            read[i][1]=sc.nextFloat();
                        }
                        count=1;
                        initialized_Chorom(deg,read, 100,numOfPoints);

                        numDataset++;
                        outPut(numDataset,writer);

                    }
                } else {
                    sc.next();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
//1
//4 2
//1 5
//2 8
//3 13
//4 20
import java.util.*;

public class GA {
    public static int counterOffsprings;
    //public static HashMap<String,Float[]>Cho=new HashMap<>();
    public static float[][]choro={};
    public static String chromosome[] = {};
    public static float[][] values= {};
   // public static int Fitness_Values[][] = {};
    //public static int Weights[]={};
    public static float totalError[]={};
    public static int mydegree;
    public static int num_chorom;
    public static int myTotalValue;
    public static int numOfSelected;
//    public static ArrayList<Integer> myValues=new ArrayList<>();
//    public static ArrayList<Integer> myWeights=new ArrayList<>();
//    public static ArrayList<String> selectedItems=new ArrayList<>();
    public static float RandomFloating(int numbits, float min, float max)
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
//    static long findRandom()
//    {
//
//        // Generate the random number
//        long num = (1 + (long)(Math.random() * 100)) % 2;
//
//        // Return the generated number
//        return num;
//    }
//    static String generateBinaryString(long  numitems)
//    {
//
//        // Stores the empty string
//        String S = "";
//        // Iterate over the range [0, N - 1]
//        for(int i = 0; i < numitems; i++)
//        {
//
//            // Store the random number
//            long x = findRandom();
//
//            // Append it to the string
//            S = S + String.valueOf(x);
//        }
//
//        // Print the resulting string
//        return S;
//    }
    //    public static BigInteger randomForBitsNonZero(int numBits) {
//        BigInteger candidate = new BigInteger(String.valueOf(numBits));
//        while(candidate.equals(BigInteger.ZERO)) {
//            candidate = new BigInteger(String.valueOf(numBits));
//        }
//        return candidate;
//    }

    //    public static String random_num(int numBits){
//        int start=0;
//        int end= (int) Math.pow(10,numBits);
//        Random number = new Random();
//        Integer randInt = number.nextInt((end - start) + 1) + start;
//        String binaryString = Integer.toBinaryString(randInt);
//        binaryString = binaryString.substring(binaryString.length() - numBits);
//      return  binaryString;
//    }
    public static void initialized_Chorom(int degree,float[][] arr, int Numchorom) {
        num_chorom=Numchorom;
        mydegree=degree;

        myTotalValue=0;
        counterOffsprings=1;
        numOfSelected=0;
        chromosome= new String[Numchorom];
        choro=new float[Numchorom][degree+1];
        //ArrayList<String> choro=new ArrayList<String>();

       for (int i = 0; i < Numchorom; i++) {
           for (int j = 0; j < degree + 1; j++) {
               chromosome[i] = "c" + (i + 1);
               choro[i][j]=RandomFloating(degree+1,arr[i][0],arr[i][1]);
               // Cho.put(chromosome[i],RandomFloating(degree+1,arr[i][0],arr[i][1]));
               // randomNUM[i] = RandomFloating(degree+1,arr[i][0],arr[i+1][1]);

           }
       }

        for (int i = 0; i < Numchorom; i++) {
            for (int j = 0; j < degree + 1; j++) {
                System.out.println(chromosome[i] +" -> "+choro[i][j]);
            }
        }


       fitness(Numchorom,degree,chromosome,choro,arr);
    }


    public static void fitness(int Numchorom,int degree,String[] choromosome,float [][]choro,float arr[][]) {
float []MeanSqu=new float[Numchorom];
float []M=new float[degree+1];
totalError=new float[Numchorom];
float result,r;
float sum = 0;
for(int i=0;i<Numchorom;i++) {
    for (int j = 0; j < Numchorom; j++)
    {
        for (int b=1;b<degree+1;b++)
        {  M[0]=choro[i][0];
            M[b] =M[b-1]+(choro[i][b]*(power(arr[j][0],b)));
        }
        result=(M[degree-1]-arr[0][1]);
        MeanSqu[j]=power(result,2);
       sum+=MeanSqu[j];
    }
    r=(float) sum/Numchorom;
    totalError[i]=(float) 1/r;
    System.out.println(i+"             tttttttttttttttttttttttttttttttttttttttt"+totalError[i]);
    sum=0;
}

for(int i=0;i<Numchorom;i++)
{
    System.out.println(chromosome[i] + " "+totalError[i] );
}
    Selection(choromosome,totalError,Numchorom,degree);

    }



    public static void Selection(String[]chromosome,float[]totalerror,int Numchorom,int degree) {
        //to calcu cummulative fitness
        float cummFitness[] = new float [Numchorom];
        cummFitness[0] = totalerror[0];

        for (int i = 1; i < Numchorom; i++) {
            cummFitness[i] = cummFitness[i - 1] + totalerror[i];

        }

        for (int i = 0; i < Numchorom; i++) {
            System.out.println(chromosome[i]+"  cummFitnessss  " + cummFitness[i]);
        }

        for (int I = 0; I < Numchorom / 2; I++) {
            float r1 = getRandomNumber(0, cummFitness[cummFitness.length - 1]);
            float r2 = getRandomNumber(0, cummFitness[cummFitness.length - 1]);
           System.out.println("     length is "+  cummFitness[cummFitness.length-1] );
            System.out.println("random num 1 is " + r1);
            System.out.println("random num 2 is " + r2);

            int numofcumm1 = 0, numofcumm2 = 0;//get in which ch this r will be
            for (int i = 0; i < Numchorom; i++) {
                if (r1 < cummFitness[i]) {
                    numofcumm1 = i;
                    break;
                }
            }
            for (int i = 0; i < Numchorom; i++) {
                if (r2 < cummFitness[i]) {
                    numofcumm2 = i;
                    break;
                }
            }

    System.out.println("num of ccccccccccccccccccccc11111 is "+numofcumm1);
    System.out.println("num of ccccccccccccccccccccc22222 is "+numofcumm2);
            System.out.println("random num 1 is in c " + chromosome[numofcumm1]);
            System.out.println("random num 2 is in c " + chromosome[numofcumm2]);

            String[] nameOfChromosomes = {chromosome[numofcumm1], chromosome[numofcumm2]};


            //crossOver(nameOfChromosomes, values);
        }
    }
    public static void FitnessOffspring(ArrayList<Float>choro)
    {

        float MeanSqu[]=new float[Numchorom];
        float M[]=new float[degree+1];
        float totalError=0;
        float result,r;
        float sum = 0;

            for (int j = 0; j < Numchorom; j++)
            {
                for (int b=1;b<degree+1;b++)
                {  M[0]=choro.get(0);
                    M[b] =M[b-1]+(choro.get(b)*(power(arr[j][0],b)));
                }
                result=(M[degree-1]-arr[0][1]);
                MeanSqu[j]=power(result,2);
                sum+=MeanSqu[j];
            }
           totalError=sum/Numchorom;
           result=1/totalError;//result contains fitness for only one Choro
    }

//    public static void crossOver(String[] nameOfChromosomes,String[] values){
//       // System.out.println("///////////////////////////CROSSOVER////////////////////////////////");
//        double Pc= 0.5;
//        Random random= new Random();
//        double r1=random.nextDouble();
//        System.out.println("R1->"+r1);
//        if(r1<=Pc){
//            //Do crossOver
//            int r2=random.nextInt(values[0].length());
//            if(r2==0) {
//                while (true) {
//                    r2 = random.nextInt(values[0].length());
//                    if (r2 != 0) {        //no zero value
//                        break;
//                    }
//                }
//            }
//            System.out.println("R2->"+r2);
//            String temp1="",temp2="";
//            temp1=values[0].substring(r2);
//            temp2=values[1].substring(r2);
//            values[0]=values[0].substring(0,r2);
//            values[0]+=temp2;
//            values[1]=values[1].substring(0,r2);
//            values[1]+=temp1;
//            System.out.println(nameOfChromosomes[0]+"->"+values[0]);
//            System.out.println(nameOfChromosomes[1]+"->"+values[1]);
//        }else {
//            System.out.println("NO CROSSOVER");
//        }
//        mutation(nameOfChromosomes,values);
//    }
//
//    public static void mutation(String[] nameOfChromosomes,String[] values){
//        System.out.println("///////////////////////////MUTATION////////////////////////////////");
//        double Pm=0.5;
//        Random random= new Random();
//        double rm;
//        for (int i=0;i<values[0].length();i++){
//            System.out.println("Num Bit->"+i);
//            rm=random.nextDouble();
//            System.out.println("Rm->"+rm);
//            if(rm<=Pm) {
//                System.out.println("MUTATION FOR THE BIT "+i+" IN CHROMOSOME 1");
//                if (values[0].charAt(i) == '1') {
//                    values[0]=values[0].substring(0,i)+'0'+values[0].substring(i+1);
//                }else if (values[0].charAt(i) == '0') {
//                    values[0]=values[0].substring(0,i)+'1'+values[0].substring(i+1);
//                }
//                System.out.println("----------------------------------");
//            }else {
//                System.out.println("No MUTATION FOR THE BIT "+i+" IN CHROMOSOME 1");
//                System.out.println("----------------------------------");
//            }
//            rm=random.nextDouble();
//            System.out.println("Rm->"+rm);
//            if(rm<=Pm) {
//                System.out.println("MUTATION FOR THE BIT "+i+" IN CHROMOSOME 2");
//                if (values[1].charAt(i) == '1') {
//                    values[1]=values[1].substring(0,i)+'0'+values[1].substring(i+1);
//                }else if (values[1].charAt(i) == '0') {
//                    values[1]=values[1].substring(0,i)+'1'+values[1].substring(i+1);
//                }
//                System.out.println("----------------------------------");
//            }else {
//                System.out.println("No MUTATION FOR THE BIT "+i+" IN CHROMOSOME 2");
//                System.out.println("----------------------------------");
//            }
//        }
//        System.out.println(nameOfChromosomes[0]+"->"+values[0]);
//        System.out.println(nameOfChromosomes[1]+"->"+values[1]);
//        replacement(nameOfChromosomes,values);
//    }
//
//    public static boolean ifOffspringIsTheBest(String nameOfChromosome,String value){
//        int sum=0;
//        int sum_weight=0;
//        boolean status=false;
//        for (int i=0;i<value.length();i++){
//            if(value.charAt(i)=='1'){
//                sum+=weights_values[i][1];
//                sum_weight +=weights_values[i][0];
//            }
//        }
//        if(sizeOfKnapsack>sum_weight) {
//            for (int i = 0; i < chromosome.length; i++) {
//                if (nameOfChromosome == chromosome[i]) {
//                    if (Fitness_Values[i][1] < sum) {
//                        myTotalValue+=sum;
//                        status = true;
//                        myValues.add(sum);
//                        myWeights.add(sum_weight);
//                        break;
//                    }
//                }
//            }
//        }
//        return status;
//    }
//    public static void replacement(String[] nameOfChromosomes,String[] values) {
//        //replacement part
//        System.out.println("///////////////////////////REPLACEMENT////////////////////////////////");
//        if(ifOffspringIsTheBest(nameOfChromosomes[0],values[0])){
//            for (int i=0;i<chromosome.length;i++) {
//                if (nameOfChromosomes[0] == chromosome[i]) {
//                    randomNUM[i] = values[0];
//                    chromosome[i]="OS"+counterOffsprings;
//                    nameOfChromosomes[0]="OS"+counterOffsprings;
//                    counterOffsprings++;
//                }
//            }
//            System.out.println("OFFSPRING OF CHROMOSOME 1 IS THE BEST");
//        }else {
//            for (int i=0;i<chromosome.length;i++) {
//                if (nameOfChromosomes[0] == chromosome[i]) {
//                    myTotalValue+=Fitness_Values[i][1];
//                    myValues.add(Fitness_Values[i][1]);
//                    myWeights.add(Weights[i]);
//                }
//            }
//            System.out.println("Parent OF CHROMOSOME 1 IS THE BEST");
//        }
//        numOfSelected++;
//        if(nameOfChromosomes[0]!=nameOfChromosomes[1]) {                 //Handle if the two chromosomes are the same
//            if (ifOffspringIsTheBest(nameOfChromosomes[1], values[1])) {
//                for (int i = 0; i < chromosome.length; i++) {
//                    if (nameOfChromosomes[1] == chromosome[i]) {
//                        randomNUM[i] = values[1];
//                        chromosome[i] = "OS" + counterOffsprings;
//                        nameOfChromosomes[1]="OS"+counterOffsprings;
//                        counterOffsprings++;
//                    }
//                }
//                System.out.println("OFFSPRING OF CHROMOSOME 2 IS THE BEST");
//            } else {
//                for (int i=0;i<chromosome.length;i++) {
//                    if (nameOfChromosomes[0] == chromosome[i]) {
//                        myTotalValue+=Fitness_Values[i][1];
//                        myValues.add(Fitness_Values[i][1]);
//                        myWeights.add(Weights[i]);
//                    }
//                }
//                System.out.println("Parent OF CHROMOSOME 2 IS THE BEST");
//            }
//
//        }
//        numOfSelected++;
//        for (int i=0;i<chromosome.length;i++){
//            System.out.println(chromosome[i]+"->"+randomNUM[i]);
//        }
//        selectedItems.add(nameOfChromosomes[0]);
//        selectedItems.add(nameOfChromosomes[1]);
//    }
//
//    public static void outPut(int numTest){
//        System.out.println("///////////////////////////THE OUTPUT////////////////////////////////");
//        System.out.println("TESTCASE: "+numTest);
//        System.out.println("NUM OF SELECTED ITEMS: "+numOfSelected);
//        System.out.println("TOTAL VALUE: "+myTotalValue);
//        System.out.println("SELECTED ITEMS: ");
//        //System.out.println(selectedItems.get(selectedItems.size()-1));
//        for (int i=1;i<numOfSelected+1;i++){
//            System.out.print(selectedItems.get(selectedItems.size()-(i)));
//            System.out.println("-> Weight: "+myWeights.get(myWeights.size()-(i))+",Value: "+myValues.get(myValues.size()-(i)));
//
//        }
//    }

    public static void main(String[] args) {
        // write your code here
        Scanner input=new Scanner(System.in);
        int numdataset,Numpoint = 0,degree=0;
        int sizeofChoro = 5;//just for test it will be 1000 or greater
        numdataset=input.nextInt();
        while(numdataset>=1)
        {
            Numpoint=input.nextInt();
            degree=input.nextInt();
            int NUM=Numpoint;
            float x[]=new float[NUM];
            float y[]=new float[NUM];
            float arr[][]=new float[NUM][2];
            int p=0;
            while (Numpoint!=0)
            {
                x[p]=input.nextFloat();
                y[p]=input.nextFloat();
                p++;
                Numpoint--;
            }
            for(int i=0;i<NUM;i++)
                for(int j=0;j<NUM-1;j++) {
                    if(j == 0) arr[i][j]=x[i];
                    if(j == 1) arr[i][j]=y[i];
                }
            initialized_Chorom(degree,arr, NUM);

           // outPut(numdataset);
            numdataset--;
        }

    }
}



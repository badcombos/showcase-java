public class word{
   static String[] letters={/*consonants*/"bcdfghjklmnpqrstvwxyz", /*vowels*/ "aeiouy"};
   static double[][] weights = {
   /*consonant weights*/{/*b*/1.492,/*c*/2.782,/*d*/4.253,/*f*/2.228,/*g*/2.015,/*h*/6.094,/*j*/0.153,/*k*/0.772,/*l*/4.025,/*m*/2.406,/*n*/6.749,/*p*/1.929,/*q*/0.095,/*r*/5.987,/*s*/6.327,/*t*/9.056,/*v*/0.978,/*w*/2.360,/*x*/0.150,/*y*/1.974,/*z*/0.074},
   /*vowels weights*/{/*a*/8.167,/*e*/12.702,/*i*/6.966,/*o*/7.507,/*u*/2.758} };
   static double[] lengths = {/*3*/.6,/*4*/2.6,/*5*/5.2,/*6*/8.5,/*7*/12.2,/*8*/14.0,/*9*/14.0,/*10*/12.6,/*11*/10.1,/*12*/7.5,/*13*/5.2,/*14*/3.2,/*15*/2.0,/*16*/1.0,/*17*/.6,/*18*/.3,/*19*/.2};
   //length weights from: http://www.ravi.io/language-word-lengths
   static java.util.HashMap<String, String[]> beg = new java.util.HashMap();//only allowed at beginging
   static java.util.HashMap<String, String[]> all = new java.util.HashMap();//can go anywhere //only for words < 7 length
   static java.util.HashMap<String, String[]> end = new java.util.HashMap();////only allowed at end
      static {
      beg.put("s",new String[]{"sch", "scr", "shr", "spl", "spr", "squ", "str"});
      beg.put("t",new String[]{"thr"});
      /*-------*/
      all.put("b",new String[]{"bl","br"});
      all.put("c",new String[]{"cl","cr"});
      all.put("d",new String[]{"dr"});
      all.put("f",new String[]{"fr","fl"});
      all.put("g",new String[]{"gl","gr"});
      all.put("m",new String[]{"mc"});
      all.put("p",new String[]{"pl","pr","ph"});
      all.put("q",new String[]{"qu"});
      all.put("s",new String[]{"sc", "sh", "sk", "sl", "sm", "sn", "sp", "st", "sw",});
      all.put("t",new String[]{"th", "tr", "tw"});
      all.put("w",new String[]{"wh", "wr"});
      /*-------*/
      end.put("c",new String[]{"ck"});
      end.put("g",new String[]{"gh"});
      end.put("n",new String[]{"ng",/*thirds*/"nth"});
      end.put("l",new String[]{"lt","lk","lf","ly"});
   }
  /***helpful onliners** to clean up code (otherwise would be unreadable even for me)***/
   /* **methods may or may not actually be one line** */
   private static int randomInt(){//returns either 0 or 1
      return randomInt(0,2);
   }
   private static int randomInt(int x, int y){//randomInt(0,10) gives number 0~9
      return java.util.concurrent.ThreadLocalRandom.current().nextInt(x,y);
   }
   private static boolean chance(int i){//chance(4) gives 1/4 chance
      return randomInt(0,i+1)==0?true:false;//i is denominator of fraction
   }
   private static double randomDouble(){//returns double between 0 or 1
      return java.util.concurrent.ThreadLocalRandom.current().nextDouble();
   }
   private static double sum(double[] weights){
      double sum=0;
      for (double x : weights){
         sum+=x;
      }
      return sum;
   }
   private static int weightedIndex(double[] array){// the idea for doing weights this way was stolen (with premission) from Micheal Einhorn
      double ranTest = randomDouble()*sum(array);
      double temp=0;
      for (int i=0; i<array.length;i++){
         temp+=array[i];
         if (temp>ranTest)
            return i;
      }
      return array.length-1;//should never run unless maybe ranTest==1
   }
      private static char randomConsonant(){
      return letters[0].charAt(weightedIndex(weights[0]));
   }
   private static char randomVowel(){
      return letters[1].charAt(weightedIndex(weights[1]));
   }
   private static char randomChar(int x){//returns random char in letters 0 returns consonant, else returns vowel 
      return x==0?randomConsonant():randomVowel();
   }
   private static String randomCharExclude(int i, char exclude){//will return random letter thats not the sent letter
      for (char temp=exclude;true;temp=randomChar(i)){
         if (temp!=exclude)
            return ""+temp;
      }
   }
   private static String randomStringFromArray(String[] array){//gets random string of string[]
      return array[randomInt(0,array.length)];
   }
   /***end helpful onliners***/
   public static String preBuilder(){
      String s = ""+randomConsonant();
      String[] temp = all.get(s);
      if (temp!=null)
         s=randomStringFromArray(temp);
      return s;
   }
   public static String suffBuilder(){
      String s = ""+randomConsonant();
      String[] temp = end.get(s);
      if (temp!=null)
         s=randomStringFromArray(temp);
      return s;
   }
   public static String syllableBuilder(){//TODO limit syllable length
      if (randomConsonant()=='q')
         return "qu"+randomVowel();
      String s = "";
      boolean x = true;
      //pre root suff
      if (chance(3)){//pre
         s=preBuilder();
         x=false;
      }
      s+=randomVowel();//root
      if (x||chance(2))//suff
         s+=suffBuilder();
      return s;
   }
   public static String randomString(){
      String s = "", temp="";
      int length=weightedIndex(lengths)+3;//+3 because word lengths starts at 3
      for (int i=0;i<length;i++){
            temp=syllableBuilder();  
         i+=temp.length();
         s+=temp;
      }
      return s;
   }
   public static void main(String[] args){
      for (int i=0;i<11;i++)
         System.out.println(randomString());
   }
}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
   public static void main(String[] var0) {
      File var1 = new File(var0[0]);

      try {
         Scanner var2 = new Scanner(var1);
         int var3 = var2.nextInt();
         int var4 = var2.nextInt();
         var2.nextLine();
         double[][] var5 = new double[var3][var4];

         while(var2.hasNext()) {
            for(int var6 = 0; var6 < var3; ++var6) {
               for(int var7 = 0; var7 < var4; ++var7) {
                  var5[var6][var7] = (double)var2.nextInt();
               }

               var2.nextLine();
            }
         }

         REF var9 = new REF(var5, true);
         var9.rowEchelonForm();
         var2.close();
      } catch (FileNotFoundException var8) {
         System.out.println("Could not open " + var0[0]);
      }

   }
}

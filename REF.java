import java.math.RoundingMode;
import java.text.DecimalFormat;

public class REF {
   private boolean reduced;
   private double[][] matrix;
   int pivot_row = -1;
   int pivot_column = -1;
   int current_row = 0;

   public REF(double[][] var1, boolean var2) {
      this.reduced = var2;
      this.matrix = var1;
      this.printMatrix();
   }

   public void rowEchelonForm() {
      System.out.println();

      int var1;
      for(var1 = 0; var1 < this.matrix[0].length; ++var1) {
         for(int var2 = this.current_row; var2 < this.matrix.length; ++var2) {
            if (this.matrix[var2][var1] != 0.0D) {
               this.pivot_column = var1;
               this.pivot_row = var2;
               break;
            }
         }

         if (this.pivot_column != -1) {
            break;
         }
      }

      if (this.pivot_column == -1) {
         this.current_row = this.matrix.length - 1;
         if (this.reduced) {
            System.out.println("Reduced: ");
            this.reducedRowEchelonForm();
         }

      } else {
         this.rowInterchange(this.pivot_row, this.current_row);
         int var10001;
         if (this.pivot_row != this.current_row) {
            var10001 = this.pivot_row + 1;
            System.out.println("Move row " + var10001 + " to row " + (this.current_row + 1) + "\n");
            this.printMatrix();
         }

         System.out.println();

         for(var1 = this.current_row + 1; var1 < this.matrix.length; ++var1) {
            if (this.matrix[var1][this.pivot_column] != 0.0D) {
               double var4 = this.matrix[var1][this.pivot_column] / this.matrix[this.current_row][this.pivot_column];
               this.rowAdd(var1, this.current_row, -var4);
               if (var4 != 0.0D) {
                  var10001 = this.current_row + 1;
                  System.out.println("Add row " + var10001 + " with scalar " + -var4 + " to row " + (var1 + 1) + "\n");
                  this.printMatrix();
                  System.out.println();
               }
            }
         }

         this.pivot_column = -1;
         this.pivot_row = -1;
         ++this.current_row;
         this.rowEchelonForm();
      }
   }

   private void reducedRowEchelonForm() {
      for(int var1 = this.current_row; var1 >= 0; --var1) {
         for(int var2 = 0; var2 < this.matrix[0].length; ++var2) {
            if (this.matrix[var1][var2] != 0.0D) {
               this.pivot_column = var2;
               this.pivot_row = var1;
               break;
            }
         }

         if (this.pivot_column != -1) {
            break;
         }
      }

      if (this.pivot_column != -1) {
         double var6 = 1.0D / this.matrix[this.pivot_row][this.pivot_column];
         this.rowMultiply(this.current_row, var6);
         if (var6 != 1.0D) {
            System.out.println("Scale row " + (this.current_row + 1) + " to 1\n");
            this.printMatrix();
         }

         System.out.println();

         for(int var3 = this.current_row - 1; var3 >= 0; --var3) {
            if (this.matrix[var3][this.pivot_column] != 0.0D) {
               double var4 = this.matrix[var3][this.pivot_column] / this.matrix[this.current_row][this.pivot_column];
               this.rowAdd(var3, this.current_row, -var4);
               if (var4 != 0.0D) {
                  int var10001 = this.current_row + 1;
                  System.out.println("Add row " + var10001 + " with scalar " + -var4 + " to row " + (var3 + 1) + "\n");
                  this.printMatrix();
                  System.out.println();
               }
            }
         }

         this.pivot_column = -1;
         this.pivot_row = -1;
         --this.current_row;
         this.reducedRowEchelonForm();
      }
   }

   public void printMatrix() {
      double[][] var1 = this.matrix;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         double[] var4 = var1[var3];
         double[] var5 = var4;
         int var6 = var4.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            double var8 = var5[var7];
            System.out.print(format(var8) + "\t");
         }

         System.out.println();
      }

   }

   public static String format(double var0) {
      DecimalFormat var2 = new DecimalFormat("#,##0.0");
      var2.setRoundingMode(RoundingMode.HALF_UP);
      String var3 = var2.format(var0);
      var3 = var3.replaceAll("^-(?=0(\\.0*)?$)", "");
      return var3;
   }

   public void rowInterchange(int var1, int var2) {
      double[] var3 = this.matrix[var2];
      this.matrix[var2] = this.matrix[var1];
      this.matrix[var1] = var3;
   }

   public void rowMultiply(int var1, double var2) {
      for(int var4 = 0; var4 < this.matrix[var1].length; ++var4) {
         double[] var10000 = this.matrix[var1];
         var10000[var4] *= var2;
      }

   }

   public void rowAdd(int var1, int var2, double var3) {
      for(int var5 = 0; var5 < this.matrix[var1].length; ++var5) {
         double[] var10000 = this.matrix[var1];
         var10000[var5] += this.matrix[var2][var5] * var3;
      }

   }
}

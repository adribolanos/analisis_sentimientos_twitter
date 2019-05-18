import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class tweetsReducing extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context){
        int total = 0;
        int suma = 0;
        try {

            for(IntWritable value : values){
                suma+= value.get();
                total = total+1;
            }
            IntWritable result = new IntWritable();
            result.set(suma);
            context.write(key, result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double promedio = suma/total;
        System.out.print("Promedio = " + promedio);
    }

}
package q2;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgDepartmentSalReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
	@Override
	public void reduce(Text key, Iterable<DoubleWritable> values, Context con) throws IOException, InterruptedException{
		DoubleWritable avgsum = new DoubleWritable();
		double sum = 0;
		double count = 0;
		double tempo = 0;
		
		for(DoubleWritable temp:values){			
			sum += temp.get();
			count++;		
		}
		
		tempo = sum / count;
		avgsum.set(tempo);
		con.write(key, avgsum);
	}
}
package q1;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
	@Override
	public void reduce(Text key, Iterable<LongWritable> values, Context con) throws IOException, InterruptedException{
		LongWritable avgsum = new LongWritable();
		long sum = 0;
		int count = 0;
		for(LongWritable temp:values){
			count++;
			sum += temp.get();
		}
		sum = sum / count;
		avgsum.set(sum);
		con.write(key, avgsum);
	}
}

package q4;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DeptManagerDetailsReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context con) throws IOException, InterruptedException{
		IntWritable opValue = new IntWritable();
		int count = 0;
		for(IntWritable temp : values){			
			count += temp.get();
		}		
		opValue.set(count);
		con.write(key, opValue);
	}
}

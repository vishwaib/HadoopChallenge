package q1;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
	@Override
	public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
		String ipValue = value.toString();
		String opKey, tempArray[];
		Text oppKey = new Text();
		LongWritable oppValue = new LongWritable();
		tempArray = ipValue.split(",");
		opKey = tempArray[0] + "," + tempArray[1] + " " + tempArray[2] + "," + tempArray[3];
		oppKey.set(opKey);
		oppValue.set(Long.parseLong(tempArray[4]));
		con.write(oppKey, oppValue);
	}
}

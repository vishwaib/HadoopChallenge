package q3;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class EmpGrowthReduce extends Reducer<Text, Text, Text, Text> {
	@Override
	public void reduce(Text key, Iterable<Text> values, Context con) throws IOException, InterruptedException{		
		Text finalopValue = new Text();
		String tempArray[], temp, opValue;
		String title = "", dept = "";
		int i =0;
		for(Text tempVal : values){
			temp = tempVal.toString();
			tempArray = temp.split(",");
			if(i > 0){
				if(title.compareTo(tempArray[0]) == 0){
					if (dept.compareTo(tempArray[1]) != 0) {
						opValue = title + "," + dept + "," + tempArray[0] + "," + tempArray[1];
						finalopValue.set(opValue);						
						con.write(key, finalopValue);
					}
				} else {
					if (dept.compareTo(tempArray[1]) == 0) {
						opValue = title + "," + dept + "," + tempArray[0] + "," + tempArray[1];
						finalopValue.set(opValue);									
						con.write(key, finalopValue);
					}
				}
			}
			title = tempArray[0];
			dept = tempArray[1];
			i++;
		}
	}
}

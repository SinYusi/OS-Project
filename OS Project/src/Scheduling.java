import java.io.*;
public class Scheduling {
    int count;
    int timeSlice;
    Process process[];
    public void run(){

        String[]splitedStr=null;
        try {
            File file = new File("src/OS.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            count = Integer.parseInt(line);
            process = new Process[count];
            for(int i = 0 ; i < count ; i++){
                process[i]=new Process();
                line = br.readLine();
                splitedStr = null;
                splitedStr = line.split(" ");
                for(int j = 0 ; j < splitedStr.length ; j++){
                    splitedStr[j] = splitedStr[j].trim();
                }
                process[i].setId(splitedStr[0]);
                process[i].setArr_time(Integer.parseInt(splitedStr[1]));
                process[i].setSer_time(Integer.parseInt(splitedStr[2]));
                process[i].setPriority(Integer.parseInt(splitedStr[3]));
            }
            timeSlice = Integer.parseInt(br.readLine());

            FCFS fcfs = new FCFS(process, count);
            fcfs.run();
            SJF sjf = new SJF(process,count);
            sjf.run();
            NpreemptyPriority npreePriorty = new NpreemptyPriority(process, count);
            npreePriorty.run();
            PreemptyPriority preemptyPriority = new PreemptyPriority(process, count);
            preemptyPriority.run();
            RR rr = new RR(process, count, timeSlice);
            rr.run();
            SRT srt = new SRT(process,count,timeSlice);
            srt.run();
            HRN hrn = new HRN(process, count);
            hrn.run();

            br.close();
            fr.close();
        }catch(FileNotFoundException e){
            System.out.println("파일이 존재하지 않습니다.");
        }catch(IOException e){
            System.out.println("파일 입출력 실패");
        }
    }
}
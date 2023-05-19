public class HRN extends Super{
    HRN(Process process[], int count){
        this.process = process;
        this.count = count;
        pwt = new int[count];
        ret = new int[count];
        prt = new int[count];
        runtime = new int[count];
        for(int i = 0 ; i < count ; i++)pwt[i]=0;
        for(int i = 0 ; i < count ; i++)ret[i]=0;
        for(int i = 0 ; i < count ; i++)prt[i]=0;
    }
    public void run(){
        System.out.println("HRN 알고리즘 실행");
        process = sort(process,1);
        for(int i = 0 ; i < count ; i++)runtime[i]=process[i].getSer_time();
        Gantt();
        Print();
    }
    public void Gantt(){
        double priority[] = new double[count];
        for(int i = 0 ; i < count ; i++){
            for(int j = 0 ; j < process[i].getSer_time() ; j++) {
                if(prt[i]==0)prt[i]=time+res_time-process[i].getArr_time();
                System.out.print(process[i].getId() + " ");
                time++;
                for(int x = i ; x < count ; x++){
                    if(x == i) continue;
                    else{
                        pwt[x]++;
                    }
                }
            }
            System.out.print("| ");
            ret[i]=time - process[i].getArr_time();
            if(i != count-1) {
                double maxpriority = pwt[i + 1] / process[i + 1].getSer_time();
                int maxpriorityindex = i + 1;
                for (int j = i + 1; j < count; j++) {
                    priority[j] = (double)pwt[j] / (double)process[j].getSer_time();
                    if (maxpriority < priority[j] && process[j].getArr_time() < time) {
                        maxpriority = priority[j];
                        maxpriorityindex = j;
                    }
                }
                Process tp = process[maxpriorityindex];
                for (int j = maxpriorityindex; j > i + 1; j--) {
                    process[j] = process[j - 1];
                }
                process[i + 1] = tp;
            }
        }
        for(int i = 0 ; i < count ; i++){
            pwt[i] -= process[i].getArr_time();
        }
        System.out.println();
    }
}
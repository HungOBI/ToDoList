package com.example.todolist;

import java.util.ArrayList;

public class WorkUtils {
    private ArrayList<Work> worksData;

    private static WorkUtils workUtils;

    public WorkUtils() {
        worksData=new ArrayList<>();
    }

    public static WorkUtils getInstance(){
        if(workUtils==null){
            workUtils=new WorkUtils();
        }
        return workUtils;
    }

    public ArrayList<Work> getAll(){
        return worksData;
    }
    public void addWork(Work work){
        if(worksData.isEmpty()){
            work.setId(0);
        }else {
            int lastWorkId = worksData.get(worksData.size() - 1).getId();
            work.setId(lastWorkId + 1);
        }
        worksData.add(work);
    }
    /*public void updateWork(int id,Work newWork){
        Work oldWork=getWorkById(id);
        if(oldWork!=null){
            oldWork.setTitle(newWork.getTitle());
            oldWork.setCreateDate(newWork.getCreateDate());
        }

    }*/
    public void deleteWork(int id){
        for(int i=0;i<worksData.size();i++){
            if(worksData.get(i).getId()==id) {
                worksData.remove(i);
                return;
            }
        }
    }
    private Work getWorkById(int id) {
        for (Work work : worksData) {
            if (work.getId() == id) {
                return work;
            }
        }
        return null;
    }

}

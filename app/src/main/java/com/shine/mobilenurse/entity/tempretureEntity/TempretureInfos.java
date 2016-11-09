package com.shine.mobilenurse.entity.tempretureEntity;

/**
 * Created by zzw on 2016/11/9.
 * 描述:
 */

public class TempretureInfos {
    //科室
    private String department;
    //床号
    private String bedNum;
    private String name;
    private String age;
    private String sex;
    //住院病历号
    private String medicalRecordsNum;
    //住院日期
    private String inToHospitalDate;

    public TempretureInfos() {
    }

    public TempretureInfos(String department, String bedNum, String name, String age, String sex, String medicalRecordsNum, String inToHospitalDate) {
        this.department = department;
        this.bedNum = bedNum;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.medicalRecordsNum = medicalRecordsNum;
        this.inToHospitalDate = inToHospitalDate;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBedNum() {
        return bedNum;
    }

    public void setBedNum(String bedNum) {
        this.bedNum = bedNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMedicalRecordsNum() {
        return medicalRecordsNum;
    }

    public void setMedicalRecordsNum(String medicalRecordsNum) {
        this.medicalRecordsNum = medicalRecordsNum;
    }

    public String getInToHospitalDate() {
        return inToHospitalDate;
    }

    public void setInToHospitalDate(String inToHospitalDate) {
        this.inToHospitalDate = inToHospitalDate;
    }



}

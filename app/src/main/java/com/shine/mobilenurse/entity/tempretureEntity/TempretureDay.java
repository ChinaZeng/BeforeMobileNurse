package com.shine.mobilenurse.entity.tempretureEntity;

/**
 * Created by zzw on 2016/11/9.
 * 描述:
 */

public class TempretureDay {

    //日期
    private String date;
    //住院天数
    private int inToHospitalNum;
    //手术后天数
    private int cutNum;
    //入量
    private String inToNum;
    //出量
    private String outNum;
    //大便 次/日
    private int shitNumByDay;
    //体重 kg
    private float weight;
    //升高 cm
    private float height;
    //脉象
    private String maiXiang;
    //舌苔
    private String sheTai;
    //时间段数据
    private TimeData[] timeDatas;
    //血压数据
    private Blood[] bloods;

    public TempretureDay() {
    }


    public TempretureDay(String date, int inToHospitalNum, int cutNum, String inToNum, String outNum, int shitNumByDay, float weight, float height, String maiXiang, String sheTai, TimeData[] timeDatas, Blood[] bloods) {
        this.date = date;
        this.inToHospitalNum = inToHospitalNum;
        this.cutNum = cutNum;

        this.inToNum = inToNum;
        this.outNum = outNum;
        this.shitNumByDay = shitNumByDay;
        this.weight = weight;
        this.height = height;
        this.maiXiang = maiXiang;
        this.sheTai = sheTai;
        this.timeDatas = timeDatas;
        this.bloods = bloods;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getInToHospitalNum() {
        return inToHospitalNum;
    }

    public void setInToHospitalNum(int inToHospitalNum) {
        this.inToHospitalNum = inToHospitalNum;
    }

    public int getCutNum() {
        return cutNum;
    }

    public void setCutNum(int cutNum) {
        this.cutNum = cutNum;
    }


    public String getInToNum() {
        return inToNum;
    }

    public void setInToNum(String inToNum) {
        this.inToNum = inToNum;
    }

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public int getShitNumByDay() {
        return shitNumByDay;
    }

    public void setShitNumByDay(int shitNumByDay) {
        this.shitNumByDay = shitNumByDay;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getMaiXiang() {
        return maiXiang;
    }

    public void setMaiXiang(String maiXiang) {
        this.maiXiang = maiXiang;
    }

    public String getSheTai() {
        return sheTai;
    }

    public void setSheTai(String sheTai) {
        this.sheTai = sheTai;
    }

    public TimeData[] getTimeDatas() {
        return timeDatas;
    }

    public void setTimeDatas(TimeData[] timeDatas) {
        this.timeDatas = timeDatas;
    }

    public Blood[] getBloods() {
        return bloods;
    }

    public void setBloods(Blood[] bloods) {
        this.bloods = bloods;
    }

    //时间段对象
    public class TimeData {
        //具体时间段  4  8  12   16  20  24
        private int timeNum;
        //脉搏 次/分
        private int pulse;
        //心率
        private int heartRate;
        //体温
        private float temperature;
        //口温
        private float mouthTemperature;
        //腋温
        private float axillaryTemperature;
        //肛温
        private float analTemperature;
        //呼吸 次/秒
        private int braveNum;
        //生命体征
        private String vitalSigns;


        public TimeData() {
        }

        public TimeData(int timeNum, int pulse, int heartRate, float temperature, float mouthTemperature, float axillaryTemperature, float analTemperature, int braveNum, String vitalSigns) {
            this.timeNum = timeNum;
            this.pulse = pulse;
            this.heartRate = heartRate;
            this.temperature = temperature;
            this.mouthTemperature = mouthTemperature;
            this.axillaryTemperature = axillaryTemperature;
            this.analTemperature = analTemperature;
            this.braveNum = braveNum;
            this.vitalSigns = vitalSigns;
        }

        public int getHeartRate() {
            return heartRate;
        }

        public void setHeartRate(int heartRate) {
            this.heartRate = heartRate;
        }

        public int getTimeNum() {
            return timeNum;
        }

        public void setTimeNum(int timeNum) {
            this.timeNum = timeNum;
        }

        public int getPulse() {
            return pulse;
        }

        public void setPulse(int pulse) {
            this.pulse = pulse;
        }

        public float getTemperature() {
            return temperature;
        }

        public void setTemperature(float temperature) {
            this.temperature = temperature;
        }

        public float getMouthTemperature() {
            return mouthTemperature;
        }

        public void setMouthTemperature(float mouthTemperature) {
            this.mouthTemperature = mouthTemperature;
        }

        public float getAxillaryTemperature() {
            return axillaryTemperature;
        }

        public void setAxillaryTemperature(float axillaryTemperature) {
            this.axillaryTemperature = axillaryTemperature;
        }

        public float getAnalTemperature() {
            return analTemperature;
        }

        public void setAnalTemperature(float analTemperature) {
            this.analTemperature = analTemperature;
        }

        public int getBraveNum() {
            return braveNum;
        }

        public void setBraveNum(int braveNum) {
            this.braveNum = braveNum;
        }

        public String getVitalSigns() {
            return vitalSigns;
        }

        public void setVitalSigns(String vitalSigns) {
            this.vitalSigns = vitalSigns;
        }
    }

    //血压对象
    public class Blood {
        //高压
        private float highBlood;
        //低压
        private float lowBlood;

        public Blood() {
        }

        public Blood(float highBlood, float lowBlood) {
            this.highBlood = highBlood;
            this.lowBlood = lowBlood;
        }

        public float getHighBlood() {
            return highBlood;
        }

        public void setHighBlood(float highBlood) {
            this.highBlood = highBlood;
        }

        public float getLowBlood() {
            return lowBlood;
        }

        public void setLowBlood(float lowBlood) {
            this.lowBlood = lowBlood;
        }
    }

}

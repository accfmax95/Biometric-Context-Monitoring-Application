package com.example.Maxwell;

import android.os.Parcel;
import android.os.Parcelable;

public class DataBaseModel implements Parcelable {
    Integer HEART_RATE;
    Integer RESPIRATORY_RATE;
    double RATE_NAUSEA;
    double RATE_HEADACHE;
    double RATE_DIARRHEA;
    double RATE_SORE_THROAT;
    double RATE_FEVER;
    double RATE_MUSCLE_PAIN;
    double RATE_SMELL_TASTE;
    double RATE_COUGH;
    double RATE_SHORTNESS_BREATH;
    double RATE_TIRED;

    protected DataBaseModel(Parcel in) {

        if (in.readByte() == 0) {

            HEART_RATE = 0;
        } else {

            HEART_RATE = in.readInt();
        }

        if (in.readByte() == 0) {

            RESPIRATORY_RATE = 0;
        } else {

            RESPIRATORY_RATE = in.readInt();
        }

        if (in.readByte() == 0) {

            RATE_NAUSEA = 0.0;
        } else {

            RATE_NAUSEA = in.readDouble();
        }

        if (in.readByte() == 0) {

            RATE_HEADACHE = 0;
        } else {

            RATE_HEADACHE = in.readDouble();
        }

        if (in.readByte() == 0) {

            RATE_DIARRHEA = 0;
        } else {

            RATE_DIARRHEA = in.readDouble();
        }

        if (in.readByte() == 0) {

            RATE_SORE_THROAT = 0;
        } else {

            RATE_SORE_THROAT = in.readDouble();
        }

        if (in.readByte() == 0) {

            RATE_FEVER = 0;
        } else {

            RATE_FEVER = in.readDouble();
        }

        if (in.readByte() == 0) {

            RATE_MUSCLE_PAIN = 0;
        } else {

            RATE_MUSCLE_PAIN = in.readDouble();
        }

        if (in.readByte() == 0) {

            RATE_SMELL_TASTE = 0;
        } else {

            RATE_SMELL_TASTE = in.readDouble();
        }

        if (in.readByte() == 0) {

            RATE_COUGH = 0;
        } else {

            RATE_COUGH = in.readDouble();
        }

        if (in.readByte() == 0) {

            RATE_SHORTNESS_BREATH = 0;
        } else {

            RATE_SHORTNESS_BREATH = in.readDouble();
        }

        if (in.readByte() == 0) {

            RATE_TIRED = 0;
        } else {

            RATE_TIRED = in.readDouble();
        }
    }

    public static final Creator<DataBaseModel> CREATOR = new Creator<DataBaseModel>() {

        @Override
        public DataBaseModel createFromParcel(Parcel in) {
            return new DataBaseModel(in);
        }

        @Override
        public DataBaseModel[] newArray(int size) {
            return new DataBaseModel[size];
        }
    };

    public DataBaseModel() {

        this.HEART_RATE = 0;
        this.RESPIRATORY_RATE = 0;
        this.RATE_NAUSEA = 0;
        this.RATE_HEADACHE = 0;
        this.RATE_DIARRHEA = 0;
        this.RATE_SORE_THROAT = 0;
        this.RATE_FEVER = 0;
        this.RATE_MUSCLE_PAIN = 0;
        this.RATE_SMELL_TASTE = 0;
        this.RATE_COUGH = 0;
        this.RATE_SHORTNESS_BREATH = 0;
        this.RATE_TIRED = 0;
    }

    public DataBaseModel(Integer HEART_RATE, Integer RESPIRATORY_RATE, double RATE_NAUSEA, double RATE_HEADACHE,
                         double RATE_DIARRHEA, double RATE_SORE_THROAT, double RATE_FEVER, double RATE_MUSCLE_PAIN,
                         double RATE_SMELL_TASTE, double RATE_COUGH, double RATE_SHORTNESS_BREATH, double RATE_TIRED) {

        this.HEART_RATE = HEART_RATE;
        this.RESPIRATORY_RATE = RESPIRATORY_RATE;
        this.RATE_NAUSEA = RATE_NAUSEA;
        this.RATE_HEADACHE = RATE_HEADACHE;
        this.RATE_DIARRHEA = RATE_DIARRHEA;
        this.RATE_SORE_THROAT = RATE_SORE_THROAT;
        this.RATE_FEVER = RATE_FEVER;
        this.RATE_MUSCLE_PAIN = RATE_MUSCLE_PAIN;
        this.RATE_SMELL_TASTE = RATE_SMELL_TASTE;
        this.RATE_COUGH = RATE_COUGH;
        this.RATE_SHORTNESS_BREATH = RATE_SHORTNESS_BREATH;
        this.RATE_TIRED = RATE_TIRED;
    }

    public Integer getHEART_RATE() {
        return HEART_RATE;
    }

    public void setHEART_RATE(Integer HEART_RATE) {
        this.HEART_RATE = HEART_RATE;
    }

    public Integer getRESPIRATORY_RATE() {
        return RESPIRATORY_RATE;
    }

    public void setRESPIRATORY_RATE(Integer RESPIRATORY_RATE) { this.RESPIRATORY_RATE = RESPIRATORY_RATE; }

    public double getRATE_HEADACHE() {
        return RATE_HEADACHE;
    }

    public void setRATE_HEADACHE(double RATE_HEADACHE) {
        this.RATE_HEADACHE = RATE_HEADACHE;
    }

    public double getRATE_DIARRHEA() {
        return RATE_DIARRHEA;
    }

    public void setRATE_DIARRHEA(double RATE_DIARRHEA) {
        this.RATE_DIARRHEA = RATE_DIARRHEA;
    }

    public double getRATE_SORE_THROAT() {
        return RATE_SORE_THROAT;
    }

    public void setRATE_SORE_THROAT(double RATE_SORE_THROAT) { this.RATE_SORE_THROAT = RATE_SORE_THROAT; }

    public double getRATE_FEVER() {
        return RATE_FEVER;
    }

    public void setRATE_FEVER(double RATE_FEVER) {
        this.RATE_FEVER = RATE_FEVER;
    }

    public double getRATE_MUSCLE_PAIN() {
        return RATE_MUSCLE_PAIN;
    }

    public void setRATE_MUSCLE_PAIN(double RATE_MUSCLE_PAIN) { this.RATE_MUSCLE_PAIN = RATE_MUSCLE_PAIN; }

    public double getRATE_SMELL_TASTE() {
        return RATE_SMELL_TASTE;
    }

    public void setRATE_SMELL_TASTE(double RATE_SMELL_TASTE) { this.RATE_SMELL_TASTE = RATE_SMELL_TASTE; }

    public double getRATE_COUGH() {
        return RATE_COUGH;
    }

    public void setRATE_COUGH(double RATE_COUGH) {
        this.RATE_COUGH = RATE_COUGH;
    }

    public double getRATE_SHORTNESS_BREATH() {
        return RATE_SHORTNESS_BREATH;
    }

    public void setRATE_SHORTNESS_BREATH(double RATE_SHORTNESS_BREATH) { this.RATE_SHORTNESS_BREATH = RATE_SHORTNESS_BREATH; }

    public double getRATE_TIRED() {
        return RATE_TIRED;
    }

    public void setRATE_TIRED(double RATE_TIRED) {
        this.RATE_TIRED = RATE_TIRED;
    }

    public double getRATE_NAUSEA() {
        return RATE_NAUSEA;
    }

    public void setRATE_NAUSEA(double RATE_NAUSEA) {
        this.RATE_NAUSEA = RATE_NAUSEA;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        if (HEART_RATE == 0) {

            dest.writeByte((byte) 0);
        } else {

            dest.writeByte((byte) 1);
            dest.writeInt(HEART_RATE);
        }

        if (RESPIRATORY_RATE == 0) {

            dest.writeByte((byte) 0);
        } else {

            dest.writeByte((byte) 1);
            dest.writeInt(RESPIRATORY_RATE);
        }

        if (RATE_NAUSEA == 0) {

            dest.writeByte((byte) 0);
        } else {

            dest.writeByte((byte) 1);
            dest.writeDouble(RATE_NAUSEA);
        }

        if (RATE_HEADACHE == 0) {

            dest.writeByte((byte) 0);
        } else {

            dest.writeByte((byte) 1);
            dest.writeDouble(RATE_HEADACHE);
        }

        if (RATE_DIARRHEA == 0) {

            dest.writeByte((byte) 0);
        } else {

            dest.writeByte((byte) 1);
            dest.writeDouble(RATE_DIARRHEA);
        }

        if (RATE_SORE_THROAT == 0) {

            dest.writeByte((byte) 0);
        } else {

            dest.writeByte((byte) 1);
            dest.writeDouble(RATE_SORE_THROAT);
        }

        if (RATE_FEVER == 0) {

            dest.writeByte((byte) 0);
        } else {

            dest.writeByte((byte) 1);
            dest.writeDouble(RATE_FEVER);
        }

        if (RATE_MUSCLE_PAIN == 0) {

            dest.writeByte((byte) 0);
        } else {

            dest.writeByte((byte) 1);
            dest.writeDouble(RATE_MUSCLE_PAIN);
        }

        if (RATE_SMELL_TASTE == 0) {

            dest.writeByte((byte) 0);
        } else {

            dest.writeByte((byte) 1);
            dest.writeDouble(RATE_SMELL_TASTE);
        }

        if (RATE_COUGH == 0) {

            dest.writeByte((byte) 0);
        } else {

            dest.writeByte((byte) 1);
            dest.writeDouble(RATE_COUGH);
        }

        if (RATE_SHORTNESS_BREATH == 0) {

            dest.writeByte((byte) 0);
        } else {

            dest.writeByte((byte) 1);
            dest.writeDouble(RATE_SHORTNESS_BREATH);
        }

        if (RATE_TIRED == 0) {

            dest.writeByte((byte) 0);
        } else {

            dest.writeByte((byte) 1);
            dest.writeDouble(RATE_TIRED);
        }
    }
}

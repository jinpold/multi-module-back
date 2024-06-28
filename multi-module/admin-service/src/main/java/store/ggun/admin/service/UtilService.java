package store.ggun.admin.service;

public interface UtilService {
    int createRandomInteger(int start, int gapBetweenStartAndEnd);

    double createRandomDouble(double start, double gapBetweenStartAndEnd);

    String createRandomTitle();

    String createRandomContent();

    String createRandomWriter();

    String createRandomCompany();

    String createRandomUsername();

    String createRandomJob();

}





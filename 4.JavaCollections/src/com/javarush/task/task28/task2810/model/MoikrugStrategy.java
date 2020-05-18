package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
//    private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data2.html";

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0")
                .referrer("http://www.google.com")
                .get();
    }

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> result = new ArrayList<>();
        int page = 0;
        boolean hasMore = true;
        try {
            do {
                Document doc = getDocument(searchString, page);
                Elements vacancies = doc.getElementsByClass("job");
                if (!vacancies.isEmpty()) {
                    for (Element element : vacancies) {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setTitle(element.getElementsByAttributeValue("class", "title").first().text());
                        vacancy.setSalary(element.getElementsByAttributeValue("title", "Зарплата").text());
                        vacancy.setCity(element.getElementsByAttributeValue("class", "location").text());
                        vacancy.setCompanyName(element.getElementsByClass("company_name").text());
                        vacancy.setSiteName("https://moikrug.ru/");
                        vacancy.setUrl("https://moikrug.ru" + element.getElementsByAttributeValue("class", "job_icon").first().getElementsByTag("a").attr("href"));
                        result.add(vacancy);
                    }
                    page++;
                } else
                    hasMore = false;
            } while (hasMore);
        } catch (IOException e) {
        }
        return result;
    }
}

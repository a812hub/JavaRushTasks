package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private Controller controller = null;
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace(".", "/") + "/vacancies.html";

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            String fileContent = getUpdatedFileContent(vacancies);
            updateFile(fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    private String getUpdatedFileContent(List<Vacancy> updVacancies) throws IOException {
        try {
            Document document = getDocument();
            Element template = document.getElementsByClass("template").first();
            Element updTemplate = template.clone();
            updTemplate.removeClass("template");
            updTemplate.removeAttr("style");
            Elements elements = document.getElementsByAttributeValue("class", "vacancy");
            elements.forEach(Element::remove);
            for (Vacancy vacancy : updVacancies) {
                Element newVacancy = updTemplate.clone();
                newVacancy.select("td.city").first().appendText(vacancy.getCity());
                newVacancy.select("td.companyName").first().appendText(vacancy.getCompanyName());
                newVacancy.select("td.salary").first().appendText(vacancy.getSalary());
                newVacancy.select("a").first().appendText(vacancy.getTitle()).attr("href", vacancy.getUrl());
                template.before(newVacancy.outerHtml());
            }
            return document.outerHtml();
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
    }

    private void updateFile(String fileContent) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(fileContent);
        }
    }
}

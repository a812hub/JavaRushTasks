package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    View view;
    Provider[] providers;

    public Model(View view, Provider... providers) {
        if (view == null || providers == null || providers.length <= 0)
            throw new IllegalArgumentException();
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city) {
        List<Vacancy> vacancies = Arrays.stream(providers)
                .map(provider -> provider.getJavaVacancies(city))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        view.update(vacancies);
    }
}

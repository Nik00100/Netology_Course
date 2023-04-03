package ru.kirillov.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SportsGymRepository {

    @Autowired
    public SportsGymRepository() {}

    public String start() {
        return "Привет спортсмен!";
    }

    public String signUp() {
        return "Доступ в зал для спортсмена открыт";
    }

    public String update() {
        return "Данные спортсмена обновлены";
    }

    public String delete() {
        return "Данные спортсмена удалены. Доступ в зал для него закрыт";
    }
}

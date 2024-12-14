package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс описания пользователя.
 */
@Data
@ToString(callSuper = false)
@EqualsAndHashCode(exclude = {"id", "name", "birthday"})
@AllArgsConstructor
@Validated
public class User extends StorageData {
    @NotBlank(message = "Email не может быть пустым", groups = Marker.OnBasic.class)
    @Email(message = "Email должен удовлетворять правилам формирования почтовых адресов.",
            groups = {Marker.OnBasic.class, Marker.OnUpdate.class})
    private String email;

    @NotBlank(message = "login не может быть пустым", groups = Marker.OnBasic.class)
    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$", message = "login должен иметь длину от 6 до 12 символов, содержать буквы и цифры.",
            groups = {Marker.OnBasic.class, Marker.OnUpdate.class})
    private String login;

    private String name;

    @PastOrPresent(message = "Дата рождения не может быть в будущем.",
            groups = {Marker.OnBasic.class, Marker.OnUpdate.class})
    private LocalDate birthday;

    @JsonIgnore
    protected Set<Integer> friends = new HashSet<>();

    /**
     * Конструктор копирования сведений о пользователе
     *
     * @param original - объект копирования
     */
    public User(User original) {
        this.id = original.getId();
        this.email = original.getEmail();
        this.login = original.getLogin();
        this.name = original.getName();
        this.birthday = original.getBirthday();
    }
}

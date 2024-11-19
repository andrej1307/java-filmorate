package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

/**
 * Класс описания пользователя.
 */
@Data
@ToString(callSuper = false)
@EqualsAndHashCode(exclude = {"id", "name"}) // при сравнении не учитывать: id, name
@AllArgsConstructor
@Validated
public class User extends StorageData {
    @NotNull(message = "Email не может отсутствовать.")
    @Email(message = "Email должен удовлетворять правилам формирования почтовых адресов.")
    private String email;

    @NotNull(message = "login не может отсутствовать.")
    @NotBlank(message = "login не может быть пустым")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$", message = "login должен иметь длину от 6 до 12 символов, содержать буквы и цифры.")
    private String login;

    private String name;

    @Past(message = "Дата рождения не может быть в будущем.")
    private LocalDate birthday;

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

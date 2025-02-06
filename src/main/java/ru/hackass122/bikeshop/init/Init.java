package ru.hackass122.bikeshop.init;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.hackass122.bikeshop.model.Product;
import ru.hackass122.bikeshop.model.Role;
import ru.hackass122.bikeshop.model.User;
import ru.hackass122.bikeshop.service.ProductService;
import ru.hackass122.bikeshop.service.UserService;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class Init {

    private final ProductService productService;
    private final UserService userService;

    public Init(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @PostConstruct
    void init() {
        String[] names = new String[]{
                "Спица 294",
                "Концевик, алю. для троса тормоза",
                "Защитная крышка якоря многоразовая",
                "Заглушка выноса руля (пластик)",
                "Оболочка скор. троса 1м.",
                "Ободная лента 20\"",
                "Гайка резьбовой рулевой колонки 1\"1/8",
                "Тормозные колодки RB-D30"
        };
        String[] descriptions = new String[]{
                "Повреждение спиц – это то, с чем очень часто сталкиваются велосипедисты. Поэтому запасные спицы в велоаптечке – это необходимость, а не роскошь. Деталь из углеродистой стали с загнутой головкой и постоянной толщиной имеет длину 294 мм. Выбор и установка спицы зачастую требуют профессионального подхода, поэтому обращайтесь к консультантам.",
                "Специальный алюминиевый наконечник для фиксации конца тормозного троса. Прочная стальная проволока в тросе надежно защищена от расплетения. Обжим концевика осуществляется с помощью простых плоскогубцев. Бюджетная модель качественной сборки.",
                "Для защиты якоря и стягивания выноса, вилки и проставочных колец велосипеда используется специальная защитная крышка, которая подходит для многоразового использования. Также эта крышка служит для предохранения трубы вилки от пыли, грязи и дождя. Она удобно крепится на любых моделях горных велосипедов, являясь стандартной. Но перед приобретением необходимо проконсультироваться и уточнить размеры.",
                "Управление велосипедом невозможно без наличия руля. Чтобы надежно закрепить его и избежать травм и ДТП, необходимо надежно закрепить металлическую трубку с помощью заглушки выноса руля. Выбирайте только качественную продукцию для вашего велосипеда!",
                "Оболочка тормозного троса 1000 мм. Очередная модель 1000 мм-вой оболочки тормозного троса. Данная оболочка предназначена для того, чтобы защищать трос от механических повреждений, а также направлять движение троса, защищать трос от износа. Имеет универсальную, достаточную для любого велосипеда длину. Рекомендуем Вам преобретать только самые качественные комплектующие и материалы, и тогда велосипед прослужит Вам дольше. По всем возникшим вопросам бращайтесь к нашим специалистам.",
                "Чтобы уберечь велокамеру от механических повреждений острого участка обода, таких как края отверстий для спиц и сами концы спиц, необходимо использовать ободную ленту. Она являет собой полоску из резины определенных размеров. Данная модель предназначается для 20-дюймовых колес. Черного цвета.",
                "Гайка, которая потребуется для фиксации резьбовой рулевой колонки. Колонки такого типа обычно устанавливаются на недорогие велосипеды начального уровня. Под шток вилки 1\"1/8. Деталь выполнена из прочного стального сплава. В случае возникновения вопросов по размерам, обращайтесь к нашим консультантам. Цвет: черный.",
                "Износ колодок - процесс неизбежный, поэтому рекомендуем иметь при себе сменные запчасти. Данная модель RB-D30 подойдет для установки на дисковые гидравлические системы от Tektro Io/Aquila/Gemini. Основными преимуществами является невысокая цена и достойное качество."
        };

        for (int i = 1; i < 9; i++) {
            Product testProduct = new Product();
            testProduct.setName(names[i-1]);
            testProduct.setDescription(descriptions[i-1]);
            testProduct.setPrice(new BigDecimal(i * 20));
            testProduct.setStock(i * 5);
            productService.saveProduct(testProduct);
        }

        Role adminRole = new Role();
        adminRole.setAuthority("ROLE_ADMIN");

        Role userRole = new Role();
        userRole.setAuthority("ROLE_USER");

        User admin = new User();
        admin.setEmail("admin@mail");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRoles(Set.of(adminRole));
        admin.setFirstName("adminFirstName");
        admin.setLastName("adminLastName");

        User user = new User();
        user.setEmail("user@mail");
        user.setUsername("user");
        user.setPassword("user");
        user.setRoles(Set.of(userRole));
        user.setFirstName("userFirstName");
        user.setLastName("userLastName");

        userService.saveUser(admin);
        userService.saveUser(user);
    }
}

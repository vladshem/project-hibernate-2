import org.dao.*;
import org.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private final SessionFactory sessionFactory;
    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;
    private final LanguageDAO languageDAO;

    public Main() {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rating.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .buildSessionFactory();
        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
    }
    public static void main(String[] args) {
        Main main = new Main();
        Customer customer = main.createCustomer();
        main.customerReturnInventoryToStore();
        main.customerRentalInventory(customer);
        main.newFilmWasMade();
    }

    private Customer createCustomer() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Store store = storeDAO.getItems(0, 1).get(0);

            City city = cityDAO.getByName("Acua");

            Address address = new Address();
            address.setAddress("Indep str, 48");
            address.setPhone("999-111-555");
            address.setCity(city);
            address.setDistrict("Trulalala");
            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setAddress(address);
            customer.setFirstName("DIANA");
            customer.setLastName("SCOTT");
            customer.setEmail("DIANA.SCOTT@sakilacustomer.org");
            customer.setStore(store);
            customer.setActive(true);
            customerDAO.save(customer);

            session.getTransaction().commit();

            return customer;
        }
    }

    private void customerReturnInventoryToStore() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Rental rental = rentalDAO.getAnyUnreturnedRental();
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.save(rental);

            session.getTransaction().commit();
        }
    }

    private void customerRentalInventory(Customer customer) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Film film = filmDAO.getFirstAvailableFilmForRent();
            Store store = storeDAO.getItems(0, 1).get(0);
            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventoryDAO.save(inventory);

            Staff staff = store.getStaff();

            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            rental.setRentalDate(LocalDateTime.now());
            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setRental(rental);
            payment.setCustomer(customer);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setStaff(staff);
            payment.setAmount(BigDecimal.valueOf(55.47));
            paymentDAO.save(payment);

            session.getTransaction().commit();

        }
    }

    private void newFilmWasMade() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Language language = languageDAO.getItems(0,20).stream().unordered().findAny().get();
            List<Category> categories = categoryDAO.getItems(0,5);
            List<Actor> actors = actorDAO.getItems(0, 20);

            Film film = new Film();
            film.setActors(new HashSet<>(actors));
            film.setLanguage(language);
            film.setRating(Rating.PG13);
            film.setLength((short) 123);
            film.setSpecialFeatures(Stream.of(Feature.TRAILERS, Feature.COMMENTARIES).collect(Collectors.toCollection(HashSet::new)));
            film.setRentalRate(BigDecimal.ZERO);
            film.setDescription("new film");
            film.setTitle("Wind");
            film.setOriginalLanguage(language);
            film.setCategories(new HashSet<>(categories));
            film.setReleaseYear(Year.now());
            film.setReplacementCost(BigDecimal.TEN);
            film.setRentalDuration((byte) 44);
            filmDAO.save(film);

            FilmText filmText = new FilmText();
            filmText.setDescription("film");
            filmText.setTitle("Wind");
            filmText.setFilm(film);
            filmText.setId(film.getId());
            filmTextDAO.save(filmText);

            session.getTransaction().commit();
        }
    }
}

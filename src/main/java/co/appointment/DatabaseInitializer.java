package co.appointment;

import co.appointment.entity.City;
import co.appointment.entity.Province;
import co.appointment.repository.CityRepository;
import co.appointment.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseInitializer implements CommandLineRunner {
    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;

    @Override
    public void run(String... args) {
        seedProvinces();
        seedCities();
    }
    private void seedProvinces() {
        List<Province> provinces = List.of(
                new Province("Eastern Cape"),
                new Province("Free State"),
                new Province("Gauteng"),
                new Province("KwaZulu-Natal"),
                new Province("Limpopo"),
                new Province("Mpumalanga"),
                new Province("Northern Cape"),
                new Province("North West"),
                new Province("Western Cape")
        );
        provinces.forEach(province -> {
            Optional<Province> optionalProvince = provinceRepository.findByName(province.getName());
            if (optionalProvince.isEmpty()) {
                provinceRepository.save(province);
            }
        });
    }
    private void seedCities() {
        List<City> cities = List.of(
                new City("Bhisho", new Province("Eastern Cape")),
                new City("Gqeberha", new Province("Eastern Cape")),
                new City("East London", new Province("Eastern Cape")),
                new City("Bloemfontein", new Province("Free State")),
                new City("Johannesburg", new Province("Gauteng")),
                new City("Pietermaritzburg", new Province("KwaZulu-Natal")),
                new City("Polokwane", new Province("Limpopo")),
                new City("Mbombela", new Province("Mpumalanga")),
                new City("Kimberley", new Province("Northern Cape")),
                new City("Mahikeng", new Province("North West")),
                new City("Cape Town", new Province("Western Cape")),
                new City("Stellenbosch", new Province("Western Cape")),
                new City("George", new Province("Western Cape")),
                new City("Paarl", new Province("Western Cape")),
                new City("Durban", new Province("KwaZulu-Natal"))
        );
        cities.forEach(city -> {
            Optional<City> optionalCity = cityRepository.findByName(city.getName());
            if(optionalCity.isEmpty()) {
                Optional<Province> optionalProvince = provinceRepository.findByName(city.getProvince().getName());
                if(optionalProvince.isEmpty()) {
                    return;
                }
                cityRepository.save(new City(city.getName(), optionalProvince.get()));
            }
        });
    }
}

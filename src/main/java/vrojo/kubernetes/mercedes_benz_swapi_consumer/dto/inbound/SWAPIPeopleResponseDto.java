package vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.inbound;

import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.AbstractDto;

import java.util.List;
/**
 * @author valentin.rojo
 */
public class SWAPIPeopleResponseDto extends AbstractDto {
    private int count;
    private String next;
    private String previous;
    private List<PersonDTO> results;

    // Getters and setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PersonDTO> getResults() {
        return results;
    }

    public void setResults(List<PersonDTO> results) {
        this.results = results;
    }

    public static class PersonDTO {
        private String name;
        private String height;
        private String mass;
        private String hair_color;
        private String skin_color;
        private String eye_color;
        private String birth_year;
        private String gender;
        private String homeworld;
        private List<String> films;
        private List<String> species;
        private List<String> vehicles;
        private List<String> starships;
        private String created;
        private String edited;
        private String url;

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getMass() {
            return mass;
        }

        public void setMass(String mass) {
            this.mass = mass;
        }

        public String getHair_color() {
            return hair_color;
        }

        public void setHair_color(String hair_color) {
            this.hair_color = hair_color;
        }

        public String getSkin_color() {
            return skin_color;
        }

        public void setSkin_color(String skin_color) {
            this.skin_color = skin_color;
        }

        public String getEye_color() {
            return eye_color;
        }

        public void setEye_color(String eye_color) {
            this.eye_color = eye_color;
        }

        public String getBirth_year() {
            return birth_year;
        }

        public void setBirth_year(String birth_year) {
            this.birth_year = birth_year;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getHomeworld() {
            return homeworld;
        }

        public void setHomeworld(String homeworld) {
            this.homeworld = homeworld;
        }

        public List<String> getFilms() {
            return films;
        }

        public void setFilms(List<String> films) {
            this.films = films;
        }

        public List<String> getSpecies() {
            return species;
        }

        public void setSpecies(List<String> species) {
            this.species = species;
        }

        public List<String> getVehicles() {
            return vehicles;
        }

        public void setVehicles(List<String> vehicles) {
            this.vehicles = vehicles;
        }

        public List<String> getStarships() {
            return starships;
        }

        public void setStarships(List<String> starships) {
            this.starships = starships;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getEdited() {
            return edited;
        }

        public void setEdited(String edited) {
            this.edited = edited;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

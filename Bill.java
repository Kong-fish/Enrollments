import java.util.List;

public class Bill {
    private double discountRate;
    private boolean includeAccommodation;

    public Bill(double discountRate, boolean includeAccommodation) {
        this.discountRate = discountRate;
        this.includeAccommodation = includeAccommodation;
    }

    public double calculateTotalFee(List<Course> courses, List<FeeComponent> feeComponents) {
        double totalFee = 0.0;

        for (Course course : courses) {
            totalFee += course.getPrice();
        }

        for (FeeComponent feeComponent : feeComponents) {
            totalFee += feeComponent.getFee();
        }

        if (includeAccommodation) {
            totalFee += 500; // Accommodation fee
        }

        return totalFee;
    }

    public double calculateDiscountedFee(List<Course> courses, List<FeeComponent> feeComponents) {
        double totalDiscountedFee = 0.0;
    
        double totalFee = calculateTotalFee(courses, List.of(
            new ITNetworkFee(100),
            new LibraryFee(50),
            new ClubSocietyFee(30)
        ));
        double discount = 0;
        // Apply discount based on course level
        char prevCourseFirstChar = ' ';
        for (Course course : courses) {
            String courseName = course.getName(); // Assuming Course class has a getName method
            char courseFirstChar = courseName.charAt(0);
            
            if(prevCourseFirstChar == ' '){
                prevCourseFirstChar = courseFirstChar;
            }
            if (courseFirstChar != 'M' && courseFirstChar != 'U' && courseFirstChar != 'P'){
                courseFirstChar = 'M';
                prevCourseFirstChar = courseFirstChar;
            }

            if (courseFirstChar != prevCourseFirstChar) {
                discount += course.getPrice() * discountRate;
            }
            prevCourseFirstChar = courseFirstChar;
        }
    
        double discountedFee = totalFee - discount;
        totalDiscountedFee += discountedFee;
    
        return totalDiscountedFee;
    }
}
//FeeComponent interface
interface FeeComponent {
    double getFee();
}
// Concrete FeeComponents
class AccommodationFee implements FeeComponent {
    private double fee;
    
    public AccommodationFee(double fee) {
        this.fee = fee;
    }
    
    @Override
    public double getFee() {
        return fee;
    }
}

class ITNetworkFee implements FeeComponent {
    private double fee;
    
    public ITNetworkFee(double fee) {
        this.fee = fee;
    }
    
    @Override
    public double getFee() {
        return fee;
    }
}

class LibraryFee implements FeeComponent {
    private double fee;
    
    public LibraryFee(double fee) {
        this.fee = fee;
    }
    
    @Override
    public double getFee() {
        return fee;
    }
}

class ClubSocietyFee implements FeeComponent {
    private double fee;
    
    public ClubSocietyFee(double fee) {
        this.fee = fee;
    }
    
    @Override
    public double getFee() {
        return fee;
    }
}
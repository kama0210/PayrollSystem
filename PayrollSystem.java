import java.util.Scanner;
public class PayrollSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //details
        String employeeName;
        String employeeID;
        String position;
        // paying Variables
        double workingHours;
        double ratePerHour;
        double grossPay;
        // deduction and final paying variables
        final double TAX_RATE = 0.10;
        final double HOLIDAY_GIFT_PER_EVENT = 200.00;
        final int NUMBER_OF_GIFTS = 4;
        double totalTax;
        double totalGiftAmount;
        double netPay;
        //input
        System.out.println("Simple Payroll System");
        System.out.print("Enter Employee Name: ");
        employeeName = input.nextLine();
        System.out.print("Enter Employee ID: ");
        employeeID = input.nextLine();
        System.out.print("Enter Position: ");
        position = input.nextLine();

        //input paying data 
        System.out.println("\n Input Monthly Work Data ");
        System.out.print("Enter Working Hours (monthly): ");
        workingHours = input.nextDouble();
        System.out.print("Enter Rate Per Hour (RM): ");
        ratePerHour = input.nextDouble();

        // counting formula
        System.out.println("\nChoose pay calculation formula:");
        System.out.println("1) Standard: Gross = hours * rate");
        System.out.println("2) Overtime: First 160 hours at normal rate, excess at 1.5x");
        System.out.println("3) Daily Average: Gross = (hours / 22) * (rate * 8)  (assume 22 working days)");
        System.out.print("Select option (1-3, default 1): ");
        int formulaChoice = 1;
        if (input.hasNextInt()) {
            formulaChoice = input.nextInt();
        } else {
            input.next();
        }

        //  gross pay according to selected formula
        switch (formulaChoice) {
            case 2:
                double regularHours = Math.min(workingHours, 160);
                double overtimeHours = Math.max(0, workingHours - 160);
                grossPay = (regularHours * ratePerHour) + (overtimeHours * ratePerHour * 1.5);
                break;
            case 3:
                double dailyHours = workingHours / 22.0; // average
                grossPay = (dailyHours * 8.0) * ratePerHour;
                break;
            case 1:
            default:
                grossPay = workingHours * ratePerHour;
                break;
        }

        // tax rate and holiday gift settings 
        System.out.printf("\nDefault tax rate is %.2f%%. Enter new tax rate (percent) or 0 to keep default: ", TAX_RATE * 100);
        double taxInput = input.nextDouble();
        double taxRateUsed = TAX_RATE;
        if (taxInput > 0) {
            taxRateUsed = taxInput / 100.0;
        }

        System.out.print("Enter holiday gift amount per event (RM) or 0 to keep default: ");
        double giftPerEventInput = input.nextDouble();
        double giftPerEventUsed = HOLIDAY_GIFT_PER_EVENT;
        if (giftPerEventInput > 0) {
            giftPerEventUsed = giftPerEventInput;
        }

        System.out.print("Enter number of holiday gift events or 0 to keep default: ");
        int giftCountInput = input.nextInt();
        int giftCountUsed = NUMBER_OF_GIFTS;
        if (giftCountInput > 0) {
            giftCountUsed = giftCountInput;
        }

        // alternate display name options
        System.out.println("\nChoose display label for employee name:");
        System.out.println("1) Name");
        System.out.println("2) Staff Name");
        System.out.println("3) Worker");
        System.out.print("Select label (1-3, default 1): ");
        int labelChoice = input.nextInt();
        String nameLabel = "Name";
        if (labelChoice == 2) nameLabel = "Staff Name";
        else if (labelChoice == 3) nameLabel = "Worker";

        // compute totals
        totalGiftAmount = giftPerEventUsed * giftCountUsed;
        totalTax = grossPay * taxRateUsed;
        netPay = (grossPay - totalTax) + totalGiftAmount;
        System.out.println("EMPLOYEE SALARY SUMMARY");
        System.out.printf("%-20s: %s%n", nameLabel, employeeName);
        System.out.printf("%-20s: %s%n", "ID", employeeID);
        System.out.printf("%-20s: %s%n", "Position", position);
        System.out.println("-------------------------------------");
        System.out.printf("%-20s: RM %.2f%n", "Gross Pay", grossPay);
        System.out.printf("%-20s: RM %.2f%n", String.format("Tax Deducted (%.0f%%)", taxRateUsed * 100), totalTax);
        System.out.printf("%-20s: RM %.2f%n", String.format("Holiday Gifts (%d events)", giftCountUsed), totalGiftAmount);
        System.out.println("-------------------------------------");
        System.out.printf("%-20s: RM %.2f%n", "Net Pay (Take Home)", netPay);
        
        input.close();
    }
}
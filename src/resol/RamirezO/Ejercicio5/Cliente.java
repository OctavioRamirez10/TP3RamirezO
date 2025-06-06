package resol.RamirezO.Ejercicio5;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Cliente implements PrestamoCalculable {

    private String nombre;
    private String dni;
    private LocalDate fechaPrestamo;
    private double montoSolicitado;
    private LocalDate fechaPactada;
    private LocalDate fechaPago;

    public Cliente(String nombre, String dni, LocalDate fechaPrestamo, double montoSolicitado, LocalDate fechaPactada, LocalDate fechaPago) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaPrestamo = fechaPrestamo;
        this.montoSolicitado = montoSolicitado;
        this.fechaPactada = fechaPactada;
        this.fechaPago = fechaPago;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Monto solicitado: $" + montoSolicitado);
        System.out.println("Fecha préstamo: " + fechaPrestamo);
        System.out.println("Fecha pactada: " + fechaPactada);
        System.out.println("Fecha de pago: " + fechaPago);
    }

    public int calcularMeses() {
        return (int) ChronoUnit.MONTHS.between(fechaPrestamo, fechaPago);
    }

    public double calcularMontoConInteresYMulta() {
        int meses = calcularMeses();
        double interes = calcularInteres(montoSolicitado, meses);
        double montoFinal = montoSolicitado + interes;
        double multa = 0;

        if (fechaPago.isAfter(fechaPactada)) {
            long diasAtraso = ChronoUnit.DAYS.between(fechaPactada, fechaPago);
            multa = montoSolicitado * 0.02 * diasAtraso;
            System.out.println("Atraso: " + diasAtraso + " días");
            System.out.println("Multa: $" + multa);
        } else {
            System.out.println("Pago en término");
        }

        montoFinal += multa;

        System.out.println("Interés: $" + interes);
        System.out.println("Monto total a pagar: $" + montoFinal);
        return montoFinal;
    }
}

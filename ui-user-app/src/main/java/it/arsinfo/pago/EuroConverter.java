package it.arsinfo.pago;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class EuroConverter extends StringToBigDecimalConverter {

    public EuroConverter(String errorMessage) {
        super(BigDecimal.ZERO, errorMessage);
    }

    @Override
    public Result<BigDecimal> convertToModel(String value, ValueContext context) {
        if (value.isEmpty()) {
            return Result.ok(null);
        }
        value = value.replaceAll("[€\\s]", "").trim();
        if (value.isEmpty()) {
            value = "0";
        }
        return super.convertToModel(value, context);
    }

    @Override
    public String convertToPresentation(BigDecimal value,
                                        ValueContext context) {
        if (value == null) {
            return convertToPresentation(BigDecimal.ZERO, context);
        }
        return super.convertToPresentation(value, context) + " €";
    }

    @Override
    protected NumberFormat getFormat(Locale locale) {

        final NumberFormat format = super.getFormat(Locale.ITALIAN);
        format.setGroupingUsed(true); // enabled thousand separators
        if (format instanceof DecimalFormat) {
            // Always display currency with two decimals
            format.setMaximumFractionDigits(2);
            format.setMinimumFractionDigits(2);
        }
        return format;
    }

    public static NumberFormat getEuroCurrency() {
        return NumberFormat.getCurrencyInstance(getLocalFromISO("EUR"));
    }

    public static Locale getLocalFromISO(String iso4217code){
        Locale toReturn = null;
        for (Locale locale : NumberFormat.getAvailableLocales()) {
            String code = NumberFormat.getCurrencyInstance(locale).
                    getCurrency().getCurrencyCode();
            if (iso4217code.equals(code)) {
                toReturn = locale;
                break;
            }
        }
        return toReturn;
    }

}

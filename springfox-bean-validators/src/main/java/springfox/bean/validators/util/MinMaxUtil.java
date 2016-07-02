package springfox.bean.validators.util;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import springfox.bean.validators.plugins.ParameterMinMaxAnnotationPlugin;
import springfox.documentation.service.AllowableRangeValues;

import com.google.common.base.Optional;

public class MinMaxUtil {
	private static final Logger LOG = LoggerFactory.getLogger(ParameterMinMaxAnnotationPlugin.class);

	public static AllowableRangeValues createAllowableValuesFromMinMaxForNumbers(Optional<Min> min, Optional<Max> max) {
		AllowableRangeValues myvalues = null;

		if (min.isPresent() && max.isPresent()) {
			LOG.debug("@Min+@Max detected: adding AllowableRangeValues to field ");
			myvalues = new AllowableRangeValues(Double.toString(min.get().value()), Double.toString(max.get().value()));

		} else if (min.isPresent()) {
			LOG.debug("@Min detected: adding AllowableRangeValues to field ");
			// TODO use Max value until "infinity" works
			myvalues = new AllowableRangeValues(Double.toString(min.get().value()), Double.toString(Double.MAX_VALUE));

		} else if (max.isPresent()) {
			// TODO use Min value until "infinity" works
			LOG.debug("@Max detected: adding AllowableRangeValues to field ");
			myvalues = new AllowableRangeValues(Double.toString(Double.MIN_VALUE), Double.toString(max.get().value()));

		}
		return myvalues;
	}

}

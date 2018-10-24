package rcpdev.todo.persistence.core.project;

import oracle.toplink.descriptors.ClassDescriptor;
import oracle.toplink.mappings.DirectToFieldMapping;

public class AfterLoad {

	public static void afterWeekInfoLoad(ClassDescriptor descriptor) {
		DirectToFieldMapping weekInfoMapping = new DirectToFieldMapping();
		weekInfoMapping.setAttributeName("weekmap");
		weekInfoMapping.setFieldName("WEEK_DATA");
		weekInfoMapping.setConverter(new WeekDataConverter());
		descriptor.addMapping(weekInfoMapping);
	}
}

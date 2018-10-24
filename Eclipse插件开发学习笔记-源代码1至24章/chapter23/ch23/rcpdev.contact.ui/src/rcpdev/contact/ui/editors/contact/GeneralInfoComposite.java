package rcpdev.contact.ui.editors.contact;

import java.text.ParsePosition;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;

import rcpdev.common.core.utils.DateUtils;
import rcpdev.common.core.utils.StringUtils;
import rcpdev.common.ui.databinding.BindingManager;
import rcpdev.common.ui.databinding.ComboBinding;
import rcpdev.common.ui.databinding.DateTextBinding;
import rcpdev.common.ui.databinding.TextBinding;
import rcpdev.contact.core.model.contact.Contact;
import rcpdev.contact.ui.common.AddressComposite;
import rcpdev.contact.ui.common.CalendarDialog;
import rcpdev.contact.ui.common.DropdownLabel;
import rcpdev.contact.ui.common.DropdownLink;
import rcpdev.contact.ui.common.ModelComposite;
import rcpdev.contact.ui.common.mp.MultiPageBlock;
import rcpdev.contact.ui.common.mp.MultiPageComposite;
import rcpdev.contact.ui.common.mp.MultiPageControl;
import rcpdev.contact.ui.editors.contact.category.CategoryDialog;

public class GeneralInfoComposite extends ModelComposite {

	private Text noteText;

	private Text categoryText;

	private Text blogText;

	private Text imText;

	private Text webPageText;

	// private MultiPageControl phoneText2;

	private MultiPageControl phoneText1;

	private Text companyText;

	private MultiPageControl emailTexts;

	private Text birthdayText;

	private Hyperlink birthdayLink;

	private CCombo genderCombo;

	private Text fullNameText;

	private FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	private MultiPageComposite addressComposite;

	final DropdownLabel emailLabel;

	final DropdownLink phoneLink1;

	final MultiPageBlock emailBlock;

	final MultiPageBlock phoneBlock;

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public GeneralInfoComposite(Composite parent, int style) {
		super(parent, style);
		final ColumnLayout columnLayout = new ColumnLayout();
		columnLayout.maxNumColumns = 2;
		setLayout(columnLayout);

		final Section personalInfoSection = toolkit.createSection(this,
				Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE
						| Section.EXPANDED);
		personalInfoSection.setText("Personal Info");

		final Composite personalInfoComposite = toolkit.createComposite(
				personalInfoSection, SWT.NONE);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = 15;
		gridLayout.verticalSpacing = 15;
		gridLayout.numColumns = 2;
		personalInfoComposite.setLayout(gridLayout);
		toolkit.paintBordersFor(personalInfoComposite);
		personalInfoSection.setClient(personalInfoComposite);

		final Hyperlink fullNameLink = toolkit.createHyperlink(
				personalInfoComposite, "Full Name", SWT.NONE);
		fullNameLink.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(final HyperlinkEvent e) {
				System.out.println("Link Activated");
			}
		});

		fullNameText = toolkit
				.createText(personalInfoComposite, null, SWT.NONE);
		fullNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		birthdayLink = toolkit.createHyperlink(personalInfoComposite,
				"Birthday", SWT.NONE);
		birthdayLink
				.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		birthdayLink.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent event) {
				CalendarDialog dialog = new CalendarDialog(getShell());
				Date nowDate = StringUtils.isEmpty(birthdayText.getText()) ? null
						: DateUtils.nFormat.parse(birthdayText.getText(),
								new ParsePosition(0));
				if (nowDate != null)
					dialog.theDate = nowDate;
				Date date = (Date) dialog.open();
				if (date != null) {
					birthdayText.setText(DateUtils.nFormat.format(date));
				}
			}
		});

		birthdayText = toolkit.createText(personalInfoComposite, null, SWT.NONE
				| SWT.READ_ONLY);
		birthdayText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		birthdayText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false));

		final Label genderLabel = toolkit.createLabel(personalInfoComposite,
				"Gender", SWT.NONE);
		genderLabel.setLayoutData(new GridData());

		genderCombo = new CCombo(personalInfoComposite, SWT.READ_ONLY
				| SWT.FLAT);
		genderCombo.setItems(new String[] { "Male", "Female" });
		toolkit.adapt(genderCombo, true, true);
		genderCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false));

		final Hyperlink categoryLink = toolkit.createHyperlink(
				personalInfoComposite, "Category", SWT.NONE);
		categoryLink.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				Shell shell = ((Hyperlink) e.widget).getShell();
				CategoryDialog dialog = new CategoryDialog(shell);
				dialog.create();
				try {
					List<String> categories = ((Contact) getModel())
							.getCategory();
					if (categories != null)
						for (int i = 0; i < categories.size(); i++)
							dialog.getCategoryComposite().getModel()
									.checkCategory(categories.get(i), true);
					if (Dialog.OK == dialog.open()) {
						((Contact) getModel()).setCategory(dialog
								.getCategoryComposite().getModel()
								.getSelected());
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		categoryText = new Text(personalInfoComposite, SWT.READ_ONLY);
		categoryText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false));
		toolkit.adapt(categoryText, true, true);

		toolkit.createLabel(personalInfoComposite, "Company", SWT.NONE);

		companyText = toolkit.createText(personalInfoComposite, null, SWT.NONE);
		companyText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false));

		final Section internetSection = toolkit.createSection(this,
				Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE
						| Section.EXPANDED);
		internetSection.setText("Internet");

		final Composite internetComposite = toolkit.createComposite(
				internetSection, SWT.NONE);
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.verticalSpacing = 15;
		gridLayout_2.horizontalSpacing = 15;
		gridLayout_2.numColumns = 2;
		internetComposite.setLayout(gridLayout_2);
		toolkit.paintBordersFor(internetComposite);
		internetSection.setClient(internetComposite);

		emailLabel = new DropdownLabel(internetComposite, SWT.NONE);
		emailLabel.setLayoutData(new GridData(60, SWT.DEFAULT));
		emailLabel.setItems(new String[] { "Email", "Email1", "Email2" });
		emailLabel.setText("Email");
		// emailLabel.getContentLabel().setLayoutData(new GridData(40,15));
		toolkit.adapt(emailLabel);

		// emailTexts = toolkit.createText(internetComposite, null, SWT.NONE);
		// emailTexts.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
		// false));
		emailTexts = new MultiPageControl(internetComposite, SWT.NONE,
				Text.class, 3);
		emailTexts.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false));
		toolkit.paintBordersFor(emailTexts);
		emailBlock = new MultiPageBlock(emailLabel, emailTexts);

		toolkit.createLabel(internetComposite, "Web Page", SWT.NONE);

		webPageText = toolkit.createText(internetComposite, null, SWT.NONE);
		webPageText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		toolkit.createLabel(internetComposite, "IM", SWT.NONE);

		imText = toolkit.createText(internetComposite, null, SWT.NONE);
		imText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		toolkit.createLabel(internetComposite, "Blog", SWT.NONE);

		blogText = toolkit.createText(internetComposite, null, SWT.NONE);
		blogText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		final Section contactMethodSection = toolkit.createSection(this,
				Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE
						| Section.EXPANDED);
		contactMethodSection.setText("Contact Method");

		final Composite contactMethodComposite = toolkit.createComposite(
				contactMethodSection, SWT.NONE);
		final GridLayout gridLayout_3 = new GridLayout();
		contactMethodComposite.setLayout(gridLayout_3);
		toolkit.paintBordersFor(contactMethodComposite);
		contactMethodSection.setClient(contactMethodComposite);

		final Group phoneGroup = new Group(contactMethodComposite, SWT.NONE);
		phoneGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		phoneGroup.setText("Phone Number");
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.verticalSpacing = 15;
		gridLayout_1.numColumns = 2;
		phoneGroup.setLayout(gridLayout_1);
		toolkit.adapt(phoneGroup);
		toolkit.paintBordersFor(phoneGroup);

		phoneLink1 = new DropdownLink(phoneGroup, SWT.NONE);
		final GridData gridData_2 = new GridData(SWT.FILL, SWT.CENTER, false,
				false);
		gridData_2.widthHint = 80;
		phoneLink1.setLayoutData(gridData_2);
		String[] items = new String[] { "Home", "Company", "Mobile", "Fax",
				"Other" };
		phoneLink1.setItems(items);
		toolkit.adapt(phoneLink1);

		phoneText1 = new MultiPageControl(phoneGroup, SWT.NONE, Text.class,
				items.length);
		phoneText1
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		toolkit.paintBordersFor(phoneText1);
		phoneBlock = new MultiPageBlock(phoneLink1, phoneText1);

		final Group addressGroup = new Group(contactMethodComposite, SWT.NONE);
		addressGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));
		addressGroup.setText("Address");
		addressGroup.setLayout(new GridLayout());
		toolkit.adapt(addressGroup);

		addressComposite = new MultiPageComposite(addressGroup, SWT.NONE);
		addressComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false));
		addressComposite.setItems(new String[] { "Home", "Company", "Other" },
				AddressComposite.class);

		final Section noteSection = toolkit.createSection(this,
				Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
		final ColumnLayoutData columnLayoutData = new ColumnLayoutData();
		columnLayoutData.heightHint = 500;
		noteSection.setLayoutData(columnLayoutData);
		noteSection.setText("Note");

		final Composite noteComposite = toolkit.createComposite(noteSection,
				SWT.NONE);
		noteComposite.setLayout(new GridLayout());
		toolkit.paintBordersFor(noteComposite);
		noteSection.setClient(noteComposite);

		noteText = toolkit.createText(noteComposite, null, SWT.WRAP
				| SWT.V_SCROLL | SWT.MULTI);
		final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.heightHint = 111;
		noteText.setLayoutData(gridData);

		//
		initBindingManager();
	}

	protected void initBindingManager() {
		model = createEmptyModel();
		manager = new BindingManager(model);
		manager.addBinding(new TextBinding("fullName", fullNameText));
		manager.addBinding(new ComboBinding("gender", genderCombo));
		manager.addBinding(new DateTextBinding("birthday", birthdayText));
		manager.addBinding(new TextBinding("category", categoryText));
		manager.addBinding(new TextBinding("company", companyText));
		manager.addBinding(new TextBinding("webPage", webPageText));
		manager.addBinding(new TextBinding("im", imText));
		manager.addBinding(new TextBinding("note", noteText));
		manager.addBinding(new TextBinding("blog", blogText));
		manager.addBinding(emailBlock.new BlockBinding("emails"));
		manager.addBinding(phoneBlock.new BlockBinding("phones"));
		manager.addBinding(addressComposite.new MPCoBinding("addresses"));
	}

	@Override
	protected IAdaptable createEmptyModel() {
		return new Contact();
	}

}

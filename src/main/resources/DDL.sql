CREATE TABLE Department
(
    DepartmentID   SERIAL PRIMARY KEY,
    DepartmentName VARCHAR(100) NOT NULL
);

CREATE TABLE Doctor
(
    DoctorID     SERIAL PRIMARY KEY,
    FirstName    VARCHAR(50) NOT NULL,
    LastName     VARCHAR(50) NOT NULL,
    ContactInfo  VARCHAR(100),
    DepartmentID INT,
    Availability VARCHAR(100),
    CONSTRAINT fk_doctor_department FOREIGN KEY (DepartmentID) REFERENCES Department (DepartmentID) ON DELETE SET NULL
);
CREATE TABLE Patient
(
    PatientID        SERIAL PRIMARY KEY,
    FirstName        VARCHAR(50) NOT NULL,
    LastName         VARCHAR(50) NOT NULL,
    DateOFBirth      VARCHAR(28)        NOT NULL,
    Gender           VARCHAR(20) CHECK (Gender IN ('Male', 'Female', 'Non-binary')),
    ContactInfo      VARCHAR(100),
    EmergencyContact VARCHAR(100),
    Address          TEXT
);
CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    FirstName VARCHAR(50)                                       NOT NULL,
    LastName  VARCHAR(50)                                       NOT NULL,
    email     VARCHAR(100) UNIQUE                               NOT NULL,
    password  VARCHAR(255)                                      NOT NULL,
    role      VARCHAR(20) CHECK (role IN ('doctor', 'patient')) NOT NULL
);
CREATE TABLE doctor_users
(
    id       SERIAL PRIMARY KEY,
    user_id  INT NOT NULL,
    doctor_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES Doctor (DoctorID) ON DELETE CASCADE
);

CREATE TABLE patient_users
(
    id        SERIAL PRIMARY KEY,
    user_id   INT NOT NULL,
    patient_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES Patient (PatientID) ON DELETE CASCADE
);
CREATE TABLE Appointment
(
    AppointmentID   SERIAL PRIMARY KEY,
    PatientID       INT       NOT NULL,
    DoctorID        INT       NOT NULL,
    AppointmentDate VARCHAR(50) NOT NULL,
    Reason          TEXT,
    Status          VARCHAR(20) DEFAULT 'Scheduled' CHECK (Status IN ('Scheduled', 'Completed', 'Cancelled')),
    CONSTRAINT fk_appointment_patient FOREIGN KEY (PatientID) REFERENCES Patient (PatientID),
    CONSTRAINT fk_appointment_doctor FOREIGN KEY (DoctorID) REFERENCES Doctor (DoctorID)
);
CREATE TABLE MedicalRecord
(
    MedicalRecordID SERIAL PRIMARY KEY,
    PatientID       INT         NOT NULL,
    RecordDate      VARCHAR(70) NOT NULL,
    Diagnosis       TEXT,
    Treatment       TEXT,
    Medication      TEXT,
    Allergies       TEXT,
    Notes           TEXT,
    FOREIGN KEY (PatientID) REFERENCES Patient (PatientID) ON DELETE CASCADE
);

INSERT INTO Department (DepartmentName)
VALUES
    ('Cardiology'),
    ('Neurology'),
    ('Pediatrics'),
    ('Orthopedics'),
    ('Oncology'),
    ('Radiology'),
    ('Emergency Medicine'),
    ('Anesthesiology'),
    ('Dermatology'),
    ('Psychiatry'),
    ('Gastroenterology');

insert into users (firstname, lastname, email, password, role) values ('Tory', 'Porter', 'tporter0@nps.gov', 'alDhntIj=)}', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Leoline', 'Ceaser', 'lceaser1@nymag.com', '6u@W<wM)K}?a', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Christiana', 'Alwin', 'calwin2@nba.com', '=Oq2J|%4)dUD', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Far', 'Snook', 'fsnook3@youtube.com', '(0zHpBQADa,v', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Theodora', 'Comford', 'tcomford4@elpais.com', 'BT4>O_UniN$!', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Merrielle', 'Woolager', 'mwoolager5@ameblo.jp', 'kK{+O.f(', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Alysia', 'Petrello', 'apetrello6@xinhuanet.com', 'e1E$p$\XfZi', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Kati', 'Plevin', 'kplevin7@homestead.com', ')}`&4"/=<wbZ', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Anetta', 'Batchelor', 'abatchelor8@shop-pro.jp', 'TEP>,l$CYc', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Gerome', 'Carbry', 'gcarbry9@ucla.edu', 'f"8r~@gn=', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Marthena', 'Noulton', 'mnoultona@ycombinator.com', '3T+7ug`dn', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Mariejeanne', 'Reeken', 'mreekenb@angelfire.com', '"_1!TzloT@o!', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Reyna', 'Goodbur', 'rgoodburc@bbc.co.uk', 'g\\a#SR', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Correy', 'Vignal', 'cvignald@artisteer.com', '|}Sj)<mR4i', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Kacey', 'Deniscke', 'kdenisckee@cnbc.com', '9%Wqw*uEXa}', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Nertie', 'Klewi', 'nklewif@tinypic.com', 'nVIDK8`HR', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Abbie', 'Fiddyment', 'afiddymentg@blogger.com', 'JAUL4R0&5%''', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Raddy', 'Byforth', 'rbyforthh@bluehost.com', '&g\\oL', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Hayes', 'Alberts', 'halbertsi@desdev.cn', 'ajW)32!', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Alane', 'Arkill', 'aarkillj@xinhuanet.com', '~Fsl08b42ap.', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Lonny', 'McCrisken', 'lmccriskenk@macromedia.com', 'xf.|?}n%', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Rania', 'Klugman', 'rklugmanl@bigcartel.com', '@GnHLt', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Allie', 'Gumey', 'agumeym@theglobeandmail.com', 'g?x4gu', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Oralla', 'McKiernan', 'omckiernann@weather.com', 'EA5>~w~\~', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Teirtza', 'Costain', 'tcostaino@tumblr.com', 'vH<@>qGa', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Shay', 'Purbrick', 'spurbrickp@networksolutions.com', '$Q+7sXB', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Idette', 'Bote', 'iboteq@miibeian.gov.cn', ',q_{1+U9', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Gabey', 'Das', 'gdasr@ycombinator.com', 'zU$EU#gJ', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Wakefield', 'Lessmare', 'wlessmares@mashable.com', '9U9Tb$', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Lurleen', 'Brute', 'lbrutet@ustream.tv', 'Pw5!W&', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Aretha', 'Codman', 'acodmanu@tmall.com', 's5}_7P1TU', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Hervey', 'Abel', 'habelv@blogs.com', 'be7HM`A#,TB', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Eugenia', 'Bottom', 'ebottomw@acquirethisname.com', 'Df_Qj74a$>ZW', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Gavan', 'Flemmich', 'gflemmichx@exblog.jp', '4FCnMd}%9K', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Price', 'Passingham', 'ppassinghamy@google.ca', 'ELPhCU7*''VP', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Henrik', 'Endrici', 'hendriciz@mayoclinic.com', '7TEF%Q)', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Gladys', 'Dufer', 'gdufer10@comcast.net', '?*Hd<.H6Q5?0', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Trula', 'Northin', 'tnorthin11@newsvine.com', 'j,M@{>KVZL', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Shay', 'Chawkley', 'schawkley12@nytimes.com', 'aWDp4q04''', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Doralin', 'Maxworthy', 'dmaxworthy13@4shared.com', 'jdV=b<kNI*i', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Lotti', 'Vinau', 'lvinau14@fda.gov', 'YPID+e3', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Carlin', 'Bathurst', 'cbathurst15@scribd.com', '%i!N)o', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Jemima', 'Johnes', 'jjohnes16@prlog.org', ',Tlqx`', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Gianina', 'Blase', 'gblase17@exblog.jp', ')%"ewq', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Marji', 'Manders', 'mmanders18@apache.org', 'oPGpYD', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Karoly', 'Herrieven', 'kherrieven19@wiley.com', '*n9?N"', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Fredrika', 'Holde', 'fholde1a@soundcloud.com', 'O}"+YjV', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Maurits', 'Valentelli', 'mvalentelli1b@squarespace.com', 'xov6P8~}', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Windy', 'Aggis', 'waggis1c@boston.com', '#3`0.Rl{?', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Hope', 'Ferreras', 'hferreras1d@weebly.com', 'cgX?2I', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Cherilynn', 'Sedcole', 'csedcole1e@webs.com', '''5n<15C', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Marieann', 'L''Amie', 'mlamie1f@zimbio.com', 'ktyi0+xY2S', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Doralynne', 'Halfhyde', 'dhalfhyde1g@samsung.com', '\mu5&iY},?f', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Nappie', 'Exposito', 'nexposito1h@vinaora.com', '<Y\2O}y6nqI', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Riccardo', 'Holdey', 'rholdey1i@whitehouse.gov', '9KSUriR}', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Brigham', 'Batting', 'bbatting1j@mit.edu', '+Y80.x''IK', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Quint', 'Dutt', 'qdutt1k@craigslist.org', 'xX$D@n>GK5', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Howey', 'Romagosa', 'hromagosa1l@mysql.com', 'FF|KuiR,GyL', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Adelice', 'Jeggo', 'ajeggo1m@china.com.cn', 'HsR\w3h', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Boote', 'Drynan', 'bdrynan1n@canalblog.com', 'V(4guCEA', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Stefanie', 'Hamper', 'shamper1o@psu.edu', 'MG}{X/', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Margret', 'Youel', 'myouel1p@webmd.com', 'ST?>Pg>', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Blakeley', 'Aldrick', 'baldrick1q@seesaa.net', 'r+#kQMRvO8Ji', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Gill', 'Davidzon', 'gdavidzon1r@theguardian.com', 'M>5w#}D', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Way', 'Howat', 'whowat1s@sun.com', 'p/x@p70I', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Rog', 'Shearmer', 'rshearmer1t@alexa.com', '~|l$1i0Y<om', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Alden', 'Pixton', 'apixton1u@dyndns.org', 'T+yJDf!L', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Amelina', 'McCollum', 'amccollum1v@apache.org', 'Xl6FrT|p', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Linus', 'Attrie', 'lattrie1w@vkontakte.ru', 'v|?@G|', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Trent', 'Gounel', 'tgounel1x@wufoo.com', 'n3=P%)', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Koo', 'Saterthwait', 'ksaterthwait1y@friendfeed.com', 'iBs05$Q', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Miller', 'Graber', 'mgraber1z@vinaora.com', 'YG{snxF', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Reade', 'MacRinn', 'rmacrinn20@techcrunch.com', 'y%+H?~RYs', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Klemens', 'Till', 'ktill21@blog.com', '|}l0KW4', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Aeriel', 'Willoughway', 'awilloughway22@netlog.com', '<0&6Yn\R\4xx', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Herold', 'Collopy', 'hcollopy23@elegantthemes.com', '"NSssXr}b`E{', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Evangelin', 'Wharrier', 'ewharrier24@timesonline.co.uk', 'QjVy13{\e', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Trix', 'Shreeves', 'tshreeves25@bing.com', '&OP<ncV', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Angelia', 'Shiell', 'ashiell26@pagesperso-orange.fr', 'xKY?z<+', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Nelson', 'Novacek', 'nnovacek27@epa.gov', 'q&#Rm}', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Lovell', 'Deluce', 'ldeluce28@cnn.com', '`eD`tU5B{''', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Betta', 'Huntar', 'bhuntar29@springer.com', 'nU7bKfFI?C', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Ginnie', 'Blackie', 'gblackie2a@state.tx.us', 'g|xm1~=SwS', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Domeniga', 'Greder', 'dgreder2b@shutterfly.com', 'w>!#k&R', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Thaddus', 'Gianettini', 'tgianettini2c@usatoday.com', '%}q#qgX@', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Roderick', 'Paynes', 'rpaynes2d@people.com.cn', 'nfM\=Hbc&S', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Perrine', 'Widmoor', 'pwidmoor2e@free.fr', 'y\aYatp`B', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Jobie', 'Palfreeman', 'jpalfreeman2f@is.gd', '}&X{+po', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Liva', 'Woosnam', 'lwoosnam2g@parallels.com', '1YVDB.OQ', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Helyn', 'Langforth', 'hlangforth2h@163.com', '5BPd+,iocIE', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Wright', 'Drummond', 'wdrummond2i@slideshare.net', '.@1Suyj', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Leigh', 'Peerless', 'lpeerless2j@nationalgeographic.com', 'DM#yOE', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Hazlett', 'Dargue', 'hdargue2k@multiply.com', 'ziuyn4|5C_', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Nathan', 'Peacocke', 'npeacocke2l@mozilla.com', '@28|\|1Tx(', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Fair', 'Pengilly', 'fpengilly2m@gravatar.com', 'va~MVX8LM', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Manda', 'Omrod', 'momrod2n@linkedin.com', 'lE4|G4tA#(Q', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Verene', 'Giampietro', 'vgiampietro2o@live.com', '8.@@4%', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Gawen', 'Walshe', 'gwalshe2p@yale.edu', 'uDbeBX', 'patient');
insert into users (firstname, lastname, email, password, role) values ('Sigfried', 'Jevon', 'sjevon2q@freewebs.com', 'v+mHOS`', 'doctor');
insert into users (firstname, lastname, email, password, role) values ('Trstram', 'Chestney', 'tchestney2r@simplemachines.org', '"sil''U{T+zJ', 'patient');

insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Sebastian', 'Grazier', '760-883-3266', 6, 'Limited Availability');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Tedmund', 'Learmonth', '648-694-3080', 5, 'Not Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Cully', 'Madrell', '593-518-6212', 7, 'Limited Availability');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Matthieu', 'Frampton', '649-881-6288', 7, 'Limited Availability');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Eleanora', 'Dudenie', '386-705-0287', 2, 'Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Germayne', 'Gillbee', '141-781-9290', 5, 'Not Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Brandise', 'Lindfors', '899-953-9899', 8, 'Not Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Fielding', 'Feaks', '806-792-9856', 5, 'Limited Availability');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Cher', 'Dewing', '946-828-4347', 11, 'Not Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Louise', 'Daviddi', '926-818-8723', 7, 'Not Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Ki', 'Hudspith', '885-145-8430', 2, 'Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Ilyssa', 'Cufley', '913-542-6600', 10, 'Not Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Carri', 'Nethercott', '233-226-7855', 4, 'Not Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Desdemona', 'Vallis', '742-130-1970', 3, 'Not Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Sharai', 'Torpie', '349-516-3427', 8, 'Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Chaunce', 'Begent', '971-530-5786', 11, 'Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Gawen', 'Hosburn', '566-481-1657', 3, 'Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Carmelina', 'Ianne', '396-532-0154', 1, 'Not Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Carole', 'Wadeling', '120-710-9140', 1, 'Not Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Kirby', 'Manske', '386-369-2748', 7, 'Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Amandi', 'Segot', '386-727-6654', 8, 'Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Randy', 'Lillegard', '158-156-9839', 7, 'Not Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Fleming', 'Dilley', '659-538-6551', 9, 'Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Dalis', 'Colquyte', '882-872-3145', 6, 'Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Amerigo', 'Corssen', '678-964-1197', 4, 'Not Available');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Rutter', 'Rance', '253-756-3159', 7, 'Limited Availability');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Neddie', 'Michurin', '537-995-0613', 2, 'Limited Availability');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Nicola', 'Burchmore', '689-704-4276', 9, 'Limited Availability');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Amata', 'Ickovitz', '652-476-7826', 8, 'Limited Availability');
insert into Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability) values ('Grange', 'Lamborne', '905-332-5388', 10, 'Not Available');


insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Gusella', 'Mendonca', '1/1/2015', 'Female', '518-536-7127', '299-958-6015', 'PO Box 37082');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Demetre', 'Dabernott', '10/27/2003', 'Male', '708-543-3800', '767-267-9992', '2nd Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Chariot', 'Warrender', '5/10/2009', 'Male', '575-805-3852', '511-997-3683', 'Room 131');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Gussi', 'Collaton', '12/28/1990', 'Female', '204-879-4326', '332-964-6006', 'Suite 33');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Mart', 'Bryant', '3/3/2023', 'Male', '314-691-0655', '741-564-4598', '3rd Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Dare', 'Freear', '4/29/2005', 'Male', '590-717-5241', '581-343-5329', 'Suite 74');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Sebastian', 'Ioselevich', '4/25/2001', 'Male', '241-846-6371', '211-209-1989', 'Suite 41');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Gibby', 'McAllen', '6/9/2012', 'Male', '570-329-9893', '878-972-0385', 'PO Box 49763');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Klaus', 'McKevitt', '8/20/2021', 'Male', '832-216-2204', '757-262-1755', 'Suite 8');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Lynelle', 'Detoc', '8/2/2004', 'Female', '197-369-4399', '402-209-0750', '3rd Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Morgan', 'Chittim', '6/23/1992', 'Male', '547-878-4092', '287-680-6077', 'Room 212');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Andra', 'Breawood', '6/29/2012', 'Female', '777-108-2545', '968-392-9427', '11th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Ruthann', 'Artiss', '11/13/1991', 'Female', '526-811-3060', '296-849-1961', 'Suite 73');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Jodie', 'Clackers', '5/8/1990', 'Female', '442-382-4269', '241-408-5804', '3rd Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Brucie', 'Daye', '10/1/2002', 'Male', '915-473-5237', '545-923-4547', '14th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Zak', 'McMakin', '8/5/2012', 'Male', '463-343-6941', '775-193-8043', 'Room 892');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Marybelle', 'Spoors', '6/14/2015', 'Female', '363-704-7055', '829-497-1503', 'PO Box 62867');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Farleigh', 'Semiraz', '8/6/1996', 'Male', '436-220-5400', '946-748-0047', 'Suite 87');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Alfy', 'Rapa', '11/1/2011', 'Male', '944-329-3694', '570-352-6123', 'PO Box 474');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Hersh', 'Olech', '5/23/1996', 'Male', '773-671-0299', '622-157-4679', 'Apt 334');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Margret', 'Gerckens', '9/7/2013', 'Female', '250-617-3331', '419-555-0531', '19th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Marina', 'Standen', '7/19/2004', 'Female', '234-722-1130', '368-469-2816', 'PO Box 31341');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Nicolle', 'Leverich', '2/2/2020', 'Female', '685-135-1518', '557-450-6926', 'Suite 71');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Creight', 'Wavell', '8/7/2021', 'Male', '731-995-6290', '408-926-2696', '9th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Merrielle', 'Scholer', '6/5/2012', 'Female', '677-830-7897', '396-833-3385', '14th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Oralie', 'Hing', '10/29/1992', 'Female', '546-247-4322', '300-891-6748', '18th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Gradeigh', 'Penhalurick', '10/11/2001', 'Male', '151-698-5696', '366-390-8835', 'PO Box 83218');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Marsh', 'Treanor', '3/7/2010', 'Male', '711-823-9314', '167-501-8195', 'PO Box 37219');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Jena', 'Lambot', '11/12/2011', 'Female', '630-301-9648', '134-162-7531', 'Suite 26');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Lila', 'Ivantyev', '9/26/2019', 'Female', '174-335-3208', '674-370-8073', 'PO Box 10945');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Jarad', 'Pettwood', '10/6/2009', 'Male', '998-346-6656', '713-804-3558', 'Room 1566');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Mohammed', 'Verna', '4/1/2011', 'Male', '320-625-4770', '114-574-6004', '14th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Frank', 'Waddilow', '12/12/2023', 'Male', '247-611-7592', '604-636-1196', 'Suite 86');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Suki', 'Tedahl', '4/25/2014', 'Female', '632-970-6850', '842-594-6307', 'PO Box 75091');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Tedi', 'Ten Broek', '7/6/2009', 'Female', '557-765-5616', '260-196-8098', 'Room 728');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Brynne', 'McTrustrie', '4/5/1992', 'Female', '367-161-3499', '406-549-6037', 'PO Box 45774');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Theobald', 'Prandi', '3/3/2006', 'Male', '229-434-3954', '905-102-3055', 'Room 1240');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Vincent', 'Learoyde', '5/16/2011', 'Male', '755-660-2926', '981-225-2933', 'PO Box 40732');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Butch', 'Whicher', '1/23/1994', 'Male', '343-385-1009', '576-699-1715', 'Suite 39');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Dolorita', 'Rapo', '1/9/2003', 'Female', '295-228-2155', '138-729-5947', '3rd Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Robbin', 'MacRonald', '8/12/1993', 'Female', '639-110-4945', '804-506-2183', 'PO Box 20928');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Jerald', 'Munt', '5/29/1996', 'Male', '851-669-6496', '696-944-7560', '7th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Radcliffe', 'Blanshard', '12/21/2017', 'Male', '157-683-0836', '288-785-2224', 'Room 1455');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Nelly', 'Eyre', '6/24/2008', 'Female', '574-886-2419', '863-592-1586', 'Apt 1935');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Luke', 'Hundy', '7/19/1994', 'Male', '831-335-3567', '283-177-9854', 'Room 1430');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Rudolph', 'Gatman', '2/21/2010', 'Male', '409-936-5439', '527-460-0821', '20th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Ivory', 'Daybell', '4/21/2012', 'Female', '153-260-2362', '792-766-6931', 'Suite 26');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Pip', 'Hopewell', '6/4/2004', 'Male', '999-408-9716', '827-188-1045', 'Room 786');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Rozina', 'Mylechreest', '6/8/1988', 'Female', '145-771-7291', '996-424-6853', 'PO Box 40810');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Husain', 'Terzo', '8/8/2005', 'Male', '647-700-5288', '967-952-0222', 'PO Box 99779');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Geri', 'Bendtsen', '8/19/1994', 'Female', '775-147-4177', '176-518-0477', 'Suite 3');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Taylor', 'Cheek', '10/23/2018', 'Male', '826-661-3844', '224-572-5876', 'Suite 14');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Dorette', 'Greeson', '8/29/1996', 'Female', '376-133-3229', '703-939-7686', 'Apt 890');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Poul', 'Trew', '8/1/1993', 'Male', '256-121-1771', '390-765-0511', 'Room 912');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Brennan', 'Banasik', '11/23/2009', 'Male', '530-630-9896', '259-473-6787', '7th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Jessi', 'Michieli', '4/3/1997', 'Female', '585-104-5635', '790-253-8658', 'Suite 79');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Abbie', 'Belshaw', '7/19/1992', 'Male', '198-341-4796', '495-964-6578', '8th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Maurizio', 'MacPaden', '12/1/2012', 'Male', '696-946-0698', '228-714-7293', 'PO Box 83565');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Janifer', 'Pindar', '3/8/2004', 'Female', '351-607-4744', '577-258-5835', 'Suite 89');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Bevin', 'Dibnah', '10/13/2005', 'Male', '763-417-8169', '877-894-1728', 'Room 409');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Sloane', 'Perks', '10/6/2015', 'Male', '773-431-7025', '546-717-7953', '4th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Malena', 'Fancett', '6/6/1998', 'Female', '340-302-4132', '146-942-6457', '6th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Jereme', 'De Bischof', '3/28/1989', 'Male', '171-903-7891', '955-178-6665', 'PO Box 35983');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Ignace', 'Dilgarno', '3/14/1989', 'Male', '752-234-9131', '996-142-4652', 'PO Box 50012');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Karin', 'Lampl', '9/24/1995', 'Female', '312-469-2083', '271-229-6164', 'PO Box 30051');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Jordana', 'Whittock', '6/1/2009', 'Female', '984-189-7383', '421-145-1792', 'Room 1216');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Genevieve', 'Acreman', '2/1/2024', 'Female', '400-345-7447', '107-746-8795', '15th Floor');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Georgiana', 'McFaell', '4/6/2024', 'Female', '299-831-5262', '854-204-0219', 'PO Box 37782');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Johnette', 'Chesswas', '4/9/1994', 'Female', '335-771-7789', '367-753-0006', 'Apt 774');
insert into Patient (firstname, lastname, dateofbirth, gender, contactInfo, emergencycontact, address) values ('Zenia', 'Lockery', '3/4/1998', 'Female', '898-742-1339', '461-763-4185', '10th Floor');

INSERT INTO MedicalRecord (PatientID, RecordDate, Diagnosis, Treatment, Medication, Allergies, Notes)
VALUES
    ( 1, '2024-12-05', 'Hypertension', 'Lifestyle changes, prescribed low-sodium diet', 'Amlodipine 5mg', 'None', 'Patient advised to monitor blood pressure daily.'),
    ( 2, '2024-12-06', 'Diabetes Type 2', 'Insulin therapy', 'Metformin 500mg', 'None', 'Regular glucose monitoring recommended.'),
    ( 3, '2024-12-07', 'Asthma', 'Inhaler therapy', 'Albuterol inhaler', 'Peanuts', 'Patient instructed to avoid allergens and use inhaler as needed.'),
    ( 11, '2024-12-08', 'Fractured Arm', 'Immobilization and casting', 'Painkillers: Ibuprofen', 'None', 'Follow-up appointment scheduled in 2 weeks for cast removal.'),
    ( 5, '2024-12-09', 'Migraine', 'Prescribed rest and hydration', 'Sumatriptan 50mg', 'None', 'Patient asked to maintain a headache diary to identify triggers.'),
    ( 6, '2024-12-10', 'Seasonal Allergy', 'Antihistamines', 'Loratadine 10mg', 'Dust mites', 'Patient educated on environmental control measures.'),
    (7, '2024-12-11', 'Acute Bronchitis', 'Cough suppressants and hydration', 'None', 'None', 'Advised to avoid smoking and irritants.'),
    ( 8, '2024-12-12', 'Hyperlipidemia', 'Statin therapy', 'Atorvastatin 20mg', 'None', 'Follow-up lipid profile test in 3 months.'),
    (9, '2024-12-13', 'Anemia', 'Iron supplements and dietary adjustments', 'Ferrous sulfate 325mg', 'None', 'Patient advised to increase iron-rich food intake.'),
    ( 10, '2024-12-14', 'Chronic Back Pain', 'Physical therapy', 'Painkillers: Acetaminophen', 'None', 'Advised ergonomic adjustments and core strengthening exercises.');

INSERT INTO Appointment (PatientID, DoctorID, AppointmentDate, Reason, Status)
VALUES
    (1, 1, '2024-12-11 10:00:00', 'Routine Checkup', 'Scheduled'),
    (2, 2, '2024-12-12 14:00:00', 'Diabetes Follow-up', 'Scheduled'),
    (3, 3, '2024-12-13 09:00:00', 'Asthma Consultation', 'Scheduled'),
    (11, 4, '2024-12-14 11:30:00', 'Fracture Review', 'Scheduled'),
    (5, 1, '2024-12-15 16:00:00', 'Migraine Treatment', 'Scheduled'),
    (6, 2, '2024-12-16 10:30:00', 'Allergy Assessment', 'Scheduled'),
    (7, 3, '2024-12-17 08:45:00', 'Bronchitis Check', 'Scheduled'),
    (8, 4, '2024-12-18 15:15:00', 'Lipid Profile Review', 'Scheduled'),
    (9, 1, '2024-12-19 13:00:00', 'Anemia Follow-up', 'Scheduled'),
    (10, 2, '2024-12-20 12:30:00', 'Back Pain Management', 'Scheduled'),
    (11, 3, '2024-12-21 09:15:00', 'Psychiatric Consultation', 'Scheduled');

INSERT INTO doctor_users (user_id, doctor_id) VALUES (36, 20);
INSERT INTO doctor_users (user_id, doctor_id) VALUES (25, 18);
INSERT INTO doctor_users (user_id, doctor_id) VALUES (24, 13);
INSERT INTO doctor_users (user_id, doctor_id) VALUES (23, 14);
INSERT INTO doctor_users (user_id, doctor_id) VALUES (18, 13);

INSERT INTO patient_users (user_id, patient_id) VALUES (9, 1);
INSERT INTO patient_users (user_id, patient_id) VALUES (11, 2);
INSERT INTO patient_users (user_id, patient_id) VALUES (12, 3);
INSERT INTO patient_users (user_id, patient_id) VALUES (13, 4);
INSERT INTO patient_users (user_id, patient_id) VALUES (15, 5);
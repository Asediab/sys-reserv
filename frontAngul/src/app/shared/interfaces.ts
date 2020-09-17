export interface User {
  id?: number;
  firstName: string;
  lastName: string;
  active?: boolean;
  establishmentId?: number;
  password?: string;
  roles?: string;
  email?: string;
}

export interface UserLogin {
  password: string;
  email: string;
}

export interface Reservation {
  id?: number;
  establishmentId: number;
  establishmentName: string;
  userId: number;
  userFirstName: string;
  validateNumber?: string;
  valid?: boolean;
  startOfReservation: Date;
  endOfReservation: Date;
  dateCreated?: Date;
  lastModifiedDate?: Date;
}

export interface Comment {
  id?: number;
  text: string;
  author: string;
  userId: number;
  establishmentId: number;
  dateCreated?: Date;
  lastModifiedDate?: Date;
}

export interface Establishment {
  id?: number;
  name: string;
  description: string;
  address: string;
  timeTable: TimeTable;
  picture?: string;
  clients_limit: number;
  typeOfEstablishment: string;
  comments?: Comment[];
  dateCreated?: Date;
  lastModifiedDate?: Date;
}

export interface TimeTable {
  id?: number;
  establishmentId: number;
  mondayAMStart: Date;
  mondayAMEnd: Date;
  mondayPMStart: Date;
  mondayPMEnd: Date;
  tuesdayAMStart: Date;
  tuesdayAMEnd: Date;
  tuesdayPMStart: Date;
  tuesdayPMEnd: Date;
  wednesdayAMStart: Date;
  wednesdayAMEnd: Date;
  wednesdayPMStart: Date;
  wednesdayPMEnd: Date;
  thursdayAMStart: Date;
  thursdayAMEnd: Date;
  thursdayPMStart: Date;
  thursdayPMEnd: Date;
  fridayAMStart: Date;
  fridayAMEnd: Date;
  fridayPMStart: Date;
  fridayPMEnd: Date;
  saturdayAMStart: Date;
  saturdayAMEnd: Date;
  saturdayPMStart: Date;
  saturdayPMEnd: Date;
  sundayAMStart: Date;
  sundayAMEnd: Date;
  sundayPMStart: Date;
  sundayPMEnd: Date;
}

export interface AuthResponse {
  access_token: string;
  expires_in: string;
  refresh_token: string;
  scope: string;
  token_type: string;
}

export interface Principal {
  principal: {
    establishmentId: number;
    firstName: string;
    lastName: string;
    id: number;
    roles: string;
  };
}

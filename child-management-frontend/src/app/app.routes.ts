import { Routes } from '@angular/router';

import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ChildComponent } from './pages/child/child.component';
import { GuardianComponent } from './guardian/guardian/guardian.component';
import { ChildGuardianComponent } from './relationship/child-guardian/child-guardian.component';
import { EmergencyContactComponent } from './emergency/emergency-contact/emergency-contact.component';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },

  {
    path: 'dashboard',
    component: DashboardComponent
  },

  {
    path: 'children',
    component: ChildComponent
  },

  {
    path: 'guardians',
    component: GuardianComponent
  },

  {
    path: 'child-guardians',
    component: ChildGuardianComponent
  },

  {
    path: 'emergency-contacts',
    component: EmergencyContactComponent
  }

];
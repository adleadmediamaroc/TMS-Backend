import { RouterModule ,Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { AppLayoutComponent } from "./layout/app.layout.component";
import { SoustraitantsComponent } from './soustraitants/soustraitants.component';

const routes:Routes = [
    {
        path:'',component: AppLayoutComponent,
        children:[
            {path: '', component: SoustraitantsComponent}
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],     
    exports: [RouterModule]
})
export class AppRoutingModule {
}

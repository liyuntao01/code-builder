import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { WebApiResultResponse } from '@core/http/web-api-result-response';
import { ${classInfo.className}Info } from '../shared/model/${classInfo.slashName}-info';

@Injectable({
providedIn: 'root'
})
export class ${classInfo.className}Service extends WebApiResultResponse {
        constructor(private http: HttpClient) {
        super();
    }

    // 分页
    getPaging(params: any): Observable<any> {
        const url = '/${classInfo.appName}/${classInfo.slashName}/page';
        return this.http.post(url, params).pipe(map(super.handleSuccess), catchError(super.handleError));
    }

    // 详情
    getDetails(id: string): Observable<any> {
        const url = `/${classInfo.appName}/${classInfo.slashName}/${r"${id}"}`;
        return this.http.get(url).pipe(map(super.handleSuccess), catchError(super.handleError));
    }

    // 新增/编辑
    createOrEdit(${classInfo.variableName}Info: ${classInfo.className}Info): Observable<any> {
        if (${classInfo.variableName}Info.id) {
            return this.http
            .put('/${classInfo.appName}/${classInfo.slashName}', ${classInfo.variableName}Info)
            .pipe(map(super.handleSuccess), catchError(super.handleError));
        } else {
            return this.http
            .post('/${classInfo.appName}/${classInfo.slashName}', ${classInfo.variableName}Info)
            .pipe(map(super.handleSuccess), catchError(super.handleError));
        }
    }

    // 删除
    deleteInfo(id: number): Observable<any> {
        const url = `/${classInfo.appName}/${classInfo.slashName}/${r"${id}"}`;
        return this.http.delete(url).pipe(map(super.handleSuccess), catchError(super.handleError));
    }
}
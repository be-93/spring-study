

import http from 'k6/http';
import {check, group, sleep, fail} from 'k6';

export let options = {
    stages: [
        { duration: '2s', target: 100 },
        { duration: '5s', target: 100 },
        { duration: '2s', target: 200 },
        { duration: '5s', target: 200 },
        { duration: '2s', target: 300 },
        { duration: '5s', target: 300 },
        { duration: '2s', target: 400 },
        { duration: '5s', target: 400 },
        { duration: '10s', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['p(99)<1000'],
    },
};

const target = 'http://localhost:8080/user/be93';

export default function () {
    let response = http.get(`${target}`);
    check(response, {
        'Stress Test - Redis X': response => response.status === 200
    });

    sleep(1);
}
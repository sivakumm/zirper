
const doFetch = (url, options, dataFn, errorFn) => {
    try {
        fetch(url, options)
        .then(response => {
            if (response.ok) {
                if (response.status !== 204){
                    return response.json();
                }
            } else {
                errorFn(response.statusText);
            }
        })
        .then(data => {
            dataFn(data);
        })
        .catch(errorFn);
    } catch (error) {
        errorFn(error.message);
    }
};

export default doFetch;
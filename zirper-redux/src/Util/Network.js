
const doFetch = async (url, options, dataFn, errorFn) => {
    try {
        const response = await fetch(url, options)
        if (response.ok) {
            if (response.status !== 204){
                dataFn(await response.json());
            }
        } else {
            throw new Error(`${response.status}: ${response.statusText}`);
        }
    } catch (error) {
        errorFn(error.message);
    }
};

export default doFetch;
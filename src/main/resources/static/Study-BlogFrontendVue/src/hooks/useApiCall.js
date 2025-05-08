const parseResponse = async (response) => {
  const contentType = response.headers.get('Content-Type');
  console.log(contentType);

  if (contentType.includes('application/json')) {
    return await response.json();
  } else if (contentType.includes('text/')) {
    return await response.text();
  } else {
    return response.blob();
  }
};

const handleResponseFailure = async (response) => {

  const errorMessage = await parseResponse(response);
  console.log(errorMessage);

};

export const useApiCall = {
  GET: async (URL) => {
    try {
      const response = await fetch(URL);

      if (!response.ok) {
        await handleResponseFailure(response);
        return null;
      }
      return await parseResponse(response);
    } catch (e) {

    }
  },
  POST: async (URL, data, credentials, contentType) => {
    console.log(data);
    const response = await fetch(URL, {
        method: 'POST',
        headers: {
          'Content-Type': contentType,
        },
        credentials: credentials,
        body: JSON.stringify(data),
      },
    );
    console.log(response);


    if (!response.ok) {
      await handleResponseFailure(response);
      return null;
    }
    return await parseResponse(response);
  },
  PUT: async (URL, data, credentials, contentType) => {
    const response = await fetch(URL, {
        method: 'PUT',
        headers: {
          'Content-Type': contentType,
        },
        credentials: credentials,
        body: JSON.stringify(data),
      },
    );

    if (!response.ok) {


      await handleResponseFailure(response);
      return null;
    }
    return await parseResponse(response);
  },
  DELETE: async (URL, credentials, contentType) => {
    const response = await fetch(URL, {
        method: 'PUT',
        headers: {
          'Content-Type': contentType,
        },
        credentials: credentials,

      },
    );

    if (!response.ok) {
      await handleResponseFailure(response);
      return null;
    }
    return await parseResponse(response);
  },

};